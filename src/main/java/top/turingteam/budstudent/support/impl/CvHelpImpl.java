package top.turingteam.budstudent.support.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import top.turingteam.budstudent.support.CvHelper;

/**
 * @author Raqtpie
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class CvHelpImpl implements CvHelper {
    @Value("${cv.endpoint}")
    private String cvEndpoint;

    @Value("${cv.object_api}")
    private String cvObjectApi;

    @Value("${cv.face_api}")
    private String cvFaceApi;


    private final RestTemplate restTemplate;

    @Override
    public String getResultFromObjectCv(String url) {
        String apiUrl = cvEndpoint + cvObjectApi;
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("url", url);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        String result;
        try {
            result = restTemplate.postForObject(apiUrl, request, String.class);
        } catch (Exception e) {
            log.error("CV服务器异常");
            return null;
        }
        return result;
    }

    @Override
    public Boolean getResultFromFaceCv(String urlA, String urlB) {
        String api = cvEndpoint + cvFaceApi;
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("realUrl", urlA);
        map.add("unknownUrl", urlB);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        Boolean flag;
        try {
            flag = restTemplate.postForObject(api, request, Boolean.class);
        } catch (RestClientException e) {
            log.error("CV服务器异常");
            return false;
        }
        return flag;
    }
}
