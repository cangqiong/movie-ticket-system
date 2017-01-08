<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cn">

<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>登录页面</title>
    <meta name="description" content="User login page" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/resources/font-awesome/4.2.0/css/font-awesome.min.css" />
    <!-- text fonts -->
    <link rel="stylesheet" href="/resources/fonts/fonts.googleapis.com.css" />
    <!-- ace styles -->
    <link rel="stylesheet" href="/resources/css/ace.min.css" />
    <!--[if lte IE 9]>
    <link rel="stylesheet" href="/resources/css/ace-part2.min.css"/>
    <![endif]-->
    <link rel="stylesheet" href="/resources/css/ace-rtl.min.css" />
    <!--[if lte IE 9]>
    <link rel="stylesheet" href="/resources/css/ace-ie.min.css"/>
    <![endif]-->
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body class="login-layout">
<div class="main-container">
    <div class="main-content">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <div class="login-container">
                    <div class="center">
                        <h1>
                            <i class="ace-icon fa fa-leaf green"></i>
                            <span class="white" id="id-text2">电影购票系统</span>
                        </h1>
                    </div>
                    <div class="space-6"></div>
                    <div class="position-relative">
                        <div id="login-box" class="login-box visible widget-box no-border">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header blue lighter bigger">
                                        <i class="ace-icon fa fa-coffee green"></i>
                                        请输入您的信息
                                    </h4>
                                    <div class="space-6"></div>
                                    <form>
                                        <fieldset>
                                            <label class="block clearfix">
                                                    <span class="block input-icon input-icon-right">
															<input type="text" id="name" class="form-control"
                                                                   placeholder="Username/mail"/>
															<i class="ace-icon fa fa-user"></i>
														</span>
                                            </label>
                                            <label class="block clearfix">
                                                    <span class="block input-icon input-icon-right">
															<input type="password" id="pass" class="form-control"
                                                                   placeholder="Password"/>
															<i class="ace-icon fa fa-lock"></i>
														</span>
                                            </label>
                                            <div class="space"></div>
                                            <div class="clearfix">
                                                <label class="inline">
                                                    <input type="checkbox" class="ace" />
                                                    <span class="lbl"> 记住</span>
                                                </label>
                                                <button type="button" class="width-35 pull-right btn btn-sm btn-primary" id="login-btn">
                                                    <i class="ace-icon fa fa-key"></i>
                                                    <span class="bigger-110" id="login-btn">登录</span>
                                                </button>
                                            </div>
                                            <div class="space-4"></div>
                                        </fieldset>
                                    </form>
                                    <div class="social-or-login center">
                                        <span class="bigger-110">第三方登录</span>
                                    </div>
                                    <div class="space-6"></div>
                                    <div class="social-login center">
                                        <a class="btn btn-primary">
                                            <i class="ace-icon fa fa-facebook"></i>
                                        </a>
                                        <a class="btn btn-info">
                                            <i class="ace-icon fa fa-twitter"></i>
                                        </a>
                                        <a class="btn btn-danger">
                                            <i class="ace-icon fa fa-google-plus"></i>
                                        </a>
                                    </div>
                                </div>
                                <!-- /.widget-main -->
                                <div class="toolbar clearfix">
                                    <div>
                                        <a href="#" data-target="#signup-box" class="user-signup-link">
                                            注册
                                            <i class="ace-icon fa fa-arrow-right"></i>
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <!-- /.widget-body -->
                        </div>
                        <!-- /.login-box -->
                        <div id="signup-box" class="signup-box widget-box no-border">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header green lighter bigger">
                                        <i class="ace-icon fa fa-users blue"></i>
                                        用户注册
                                    </h4>
                                    <div class="space-6"></div>
                                    <p>填写信息: </p>
                                    <form>
                                        <fieldset>
                                            <label class="block clearfix">
                                                    <span class="block input-icon input-icon-right">
															<input type="email" id ="r-mail" class="form-control" placeholder="邮箱"/>
															<i class="ace-icon fa fa-envelope"></i>
														</span>
                                            </label>
                                            <label class="block clearfix">
                                                    <span class="block input-icon input-icon-right">
															<input type="text" id ="r-user" class="form-control" placeholder="用户名"/>
															<i class="ace-icon fa fa-user"></i>
														</span>
                                            </label>
                                            <label class="block clearfix">
                                                    <span class="block input-icon input-icon-right">
															<input type="password" id ="r-pass" class="form-control"
                                                                   placeholder="密码"/>
															<i class="ace-icon fa fa-lock"></i>
														</span>
                                            </label>
                                            <label class="block clearfix">
                                                    <span class="block input-icon input-icon-right">
															<input type="password" class="form-control" id ="r-second-pass"
                                                                   placeholder="确认密码"/>
															<i class="ace-icon fa fa-retweet"></i>
														</span>
                                            </label>
                                            <label class="block">
                                                <input type="checkbox" class="ace" />
                                                <span class="lbl">
															接受
															<a href="#">用户协议</a>
														</span>
                                            </label>
                                            <div class="space-24"></div>
                                            <div class="clearfix">
                                                <button type="reset" class="width-30 pull-left btn btn-sm">
                                                    <i class="ace-icon fa fa-refresh"></i>
                                                    <span class="bigger-110">重置</span>
                                                </button>
                                                <button type="button" class="width-65 pull-right btn btn-sm btn-success" id="register-btn">
                                                    <span class="bigger-110">注册</span>
                                                    <i class="ace-icon fa fa-arrow-right icon-on-right"></i>
                                                </button>
                                            </div>
                                        </fieldset>
                                    </form>
                                </div>
                                <div class="toolbar center">
                                    <a href="#" data-target="#login-box" class="back-to-login-link">
                                        <i class="ace-icon fa fa-arrow-left"></i> 返回登录
                                    </a>
                                </div>
                            </div>
                            <!-- /.widget-body -->
                        </div>
                        <!-- /.signup-box -->
                    </div>
                    <!-- /.position-relative -->
                    <div class="navbar-fixed-top align-right">
                        <br/> &nbsp;
                        <a id="btn-login-dark" href="#">Dark</a> &nbsp;
                        <span class="blue">/</span> &nbsp;
                        <a id="btn-login-blur" href="#">Blur</a> &nbsp;
                        <span class="blue">/</span> &nbsp;
                        <a id="btn-login-light" href="#">Light</a> &nbsp; &nbsp; &nbsp;
                    </div>
                </div>
            </div>
            <!-- /.col -->
        </div>
        <!-- /.row -->
    </div>
    <!-- /.main-content -->
</div>
<!-- /.main-container -->
<!-- basic scripts -->
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="/resources/js/login.js"></script>
<!-- inline scripts related to this page -->
<script type="text/javascript">
    jQuery(function($) {
        $(document).on('click', '.toolbar a[data-target]', function(e) {
            e.preventDefault();
            var target = $(this).data('target');
            $('.widget-box.visible').removeClass('visible'); //hide others
            $(target).addClass('visible'); //show target
        });
    });


    //you don't need this, just used for changing background
    jQuery(function($) {
        $('#btn-login-dark').on('click', function(e) {
            $('body').attr('class', 'login-layout');
            $('#id-text2').attr('class', 'white');
            $('#id-company-text').attr('class', 'blue');

            e.preventDefault();
        });
        $('#btn-login-light').on('click', function(e) {
            $('body').attr('class', 'login-layout light-login');
            $('#id-text2').attr('class', 'grey');
            $('#id-company-text').attr('class', 'blue');

            e.preventDefault();
        });
        $('#btn-login-blur').on('click', function(e) {
            $('body').attr('class', 'login-layout blur-login');
            $('#id-text2').attr('class', 'white');
            $('#id-company-text').attr('class', 'light-blue');

            e.preventDefault();
        });

    });

    // 初始化
    $(function() {
        loginModel.init();
    });
</script>
</body>

</html>


