package RestProject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestTemplateApp {


    public static void main(String[] args) {
        SpringApplication.run(RestTemplateApp.class, args);

        String uri = "http://91.241.64.178:7081/api/users";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> entity = restTemplate.getForEntity(uri, String.class);
        System.out.println(entity.getBody());

        String headers = entity.getHeaders().get("Set-Cookie").get(0);
        String[] cookie = headers.split(";");

        for (String cook : cookie) {
            System.out.println(cook);
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Cookie", cookie[0]);

        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.POST,
                new HttpEntity<>(new User(3L, "James", "Brown", (byte) 35), httpHeaders), String.class);
        System.out.println(responseEntity.getBody());

        ResponseEntity<String> responseEntityPut = restTemplate.exchange(uri, HttpMethod.PUT,
                new HttpEntity<>(new User(3L, "Thomas", "Shelby", (byte) 35), httpHeaders), String.class);
        System.out.println(responseEntityPut.getBody());

        ResponseEntity<String> responseEntityRemove = restTemplate.exchange(uri + "/3", HttpMethod.DELETE,
                new HttpEntity<>(httpHeaders), String.class);
        System.out.println(responseEntityRemove.getBody());

    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class User {
        private Long id;
        private String name;
        private String lastName;
        private byte age;

        @Override
        public String toString() {
            return "\nUser{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

}

