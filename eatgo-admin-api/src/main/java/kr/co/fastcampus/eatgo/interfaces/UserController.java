package kr.co.fastcampus.eatgo.interfaces;

import com.sun.org.apache.xerces.internal.impl.dv.xs.QNameDV;
import kr.co.fastcampus.eatgo.application.UserService;
import kr.co.fastcampus.eatgo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class UserController {
    // 1. user List
    // 2. User Create - > 회원가입
    // 3. User Update
    // 4. User delete -> level : 0 = 아무고토 못하죠
    // (1 : customer , 2: restaurant owner , 3: admin )

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody User resource) throws URISyntaxException {
        String email = resource.getEmail();
        String name = resource.getName();

        User user = userService.addUser(email, name);

        String url = "/users/" + user.getId();

        return ResponseEntity.created(new URI(url)).body("{}");
    }

    @GetMapping("/users")
    public List<User> list(){
        List<User> users = userService.getUsers();
        return users;
    }

    @PatchMapping("/users/{id}")
    public String update(@PathVariable("id") Long id, @RequestBody User resource) {
        String email = resource.getEmail();
        String name = resource.getName();
        Long level = resource.getLevel();

        userService.updateUser(id,email, name, level);
        return "{}";
    }

    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable("id") Long id){
        userService.deactiveUser(id);
        return "{}";
    }
}
