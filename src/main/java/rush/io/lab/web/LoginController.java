package rush.io.lab.web;

import rush.io.lab.dto.Result;
import rush.io.lab.entity.User;
import rush.io.lab.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 登录控制器
 *
 * @author cang
 * @create_time 2017-01-02 19:05
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String list(@RequestParam(value = "backurl", required = false) String backurl, RedirectAttributesModelMap modelMap) {
        if (backurl != null) {
            modelMap.addAttribute("backurl", backurl);
        }
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> login(@RequestParam(value = "username", required = true) String username,
                                @RequestParam(value = "password", required = true) String password, HttpSession session) {
        User user = userService.loginByUsername(username, password);
        Result<String> result = null;
        if (user != null) {
            session.setAttribute("username", user.getUsername());
            result = new Result<String>(true, user.getUserId(), null);
        } else {
            result = new Result<String>(false, null, "登录失败");
        }
        return result;
    }

    @RequestMapping(value = "/loginByMail", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> loginByMail(@RequestParam(value = "mail", required = true) String mail,
                                      @RequestParam(value = "password", required = true) String password, HttpSession session) {
        User user = userService.loginByMail(mail, password);
        Result<String> result = null;
        if (user != null) {
            session.setAttribute("username", user.getUsername());
            result = new Result<String>(true, user.getUserId(), null);
        } else {
            result = new Result<String>(false, null, "登录失败");
        }
        return result;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> register(@RequestParam(value = "mail", required = true) String mail, @RequestParam(value = "username", required = true) String username,
                                    @RequestParam(value = "password", required = true) String password, HttpSession session) {
        Result<Boolean> result = null;
        Map<String, Object> map = userService.registerByMail(mail, username, password);
        if ("success".equals(map.get("status"))) {
            result = new Result<Boolean>(true);
        } else {
            result = new Result<Boolean>(false, (String) map.get("error"));
        }
        return result;
    }

    @RequestMapping("/resetPasswd")
    @ResponseBody
    public Result<Boolean> resetPasswd(@RequestParam(value = "username", required = true) String username,
                                       @RequestParam(value = "password", required = true) String password) {
        Result<Boolean> result = null;
        Map<String, Object> map = userService.resetPasswd(username, password);
        if ("success".equals(map.get("status"))) {
            result = new Result<Boolean>(true);
        } else {
            result = new Result<Boolean>(false, (String) map.get("error"));
        }
        return result;
    }

    @RequestMapping("/logout")
    @ResponseBody
    public Result<Boolean> logout(HttpSession session) {
        Result<Boolean> result = new Result<Boolean>(true);
        if (session != null) {
            session.removeAttribute("userId");
            session.removeAttribute("username");
            session.invalidate();
        }
        return result;
    }

}
