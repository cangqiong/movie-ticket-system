var loginModel = {
    // 封装秒杀相关ajax的url
    url: {
        login: function() {
            return '/login/login';
        },
        loginByMail: function() {
            return '/login/loginByMail';
        },
        logout: function(ticketId, md5) {
            return '/login/logout';
        },
        regist: function(ticketId, md5) {
            return '/login/register';
        }
    },

    init: function() {
        // 绑定登录按钮事件
        $('#login-btn').on('click', loginModel.login);
        $('#register-btn').on('click', loginModel.regeister);
    },
    login: function() {
        // 1.先禁用按钮
        $(this).addClass('disabled');
        var pass = $('#pass').val();

        var name = $('#name').val();
        var data = {};
        var logUrl = loginModel.url.login();
        if (loginModel.util.isEmail(name)) {
            data = {
                "mail": name,
                "password": pass
            };
            logUrl = loginModel.url.loginByMail();
        } else {
            data = {
                "username": name,
                "password": pass
            };
        }

        // 发送登录请求
        $.ajax({
            type: "POST",
            url: logUrl,
            data: data,
            dataType: "json",
            success: function(result) {
                if (result && result['success']) {
                    // 登录成功,跳转页面
                    var userId = result['data'];
                    // 设置cookie
                    $.cookie('userId', userId, {expirs: 1, path: '/'});
                    var address = loginModel.util.getQueryString("backurl");
                    if (address != null && address.toString().length > 1) {
                        window.location.href = address;
                    } else {
                        alert("登录成功，跳转到首页");
                        window.location.href = "/";
                    }
                } else {
                    var error = result['error'];
                    $('#login-btn').removeClass('disabled');
                    alert(error + ':用户名或密码错误');
                }
            }
        });
    },
    regeister: function() {
        $(this).addClass('disabled');
        var mail = $('#r-mail').val();
        var username = $('#r-user').val();
        var pass = $('#r-pass').val();
        var secondPass = $('#r-second-pass').val();
        if (!loginModel.util.isEmail(mail)) {
            alert('请输入正确邮箱');
            $(this).removeClass('disabled');
            return;
        }
        if (username == "" || username.length < 4 || username.length > 20) {
            alert('用户名格式不正确');
            $(this).removeClass('disabled');
            return;
        }
        if (pass == "" || pass.length < 4 || pass.length > 20) {
            alert('密码格式不正确');
            $(this).removeClass('disabled');
            return;
        }
        if (pass != secondPass) {
            alert('两次密码不一致');
            $(this).removeClass('disabled');
            return;
        }
        var data = {
            "mail": mail,
            "username": username,
            "password": pass
        };
        $.post(loginModel.url.regist(), data, function(result) {
            if (result && result['success']) {
                alert("注册成功，请直接登录");
                // 注册成功，跳转到登录页面
                window.location.href = loginModel.url.login();
            } else {
                var error = result['error'];
                $('#register-btn').removeClass('disabled');
                alert(error);
            }
        });
        $(this).removeClass('disabled');
    },
    util: {
        isEmail: function(str) {
            var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
            return reg.test(str);
        },
        getQueryString: function(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]);
            return null;
        }
    }
};