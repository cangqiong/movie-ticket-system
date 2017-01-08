// 抢票模块
var grapTicketModel = {

    // 封装秒杀相关ajax的url
    url: {
        now: function() {
            return '/ticket/time/now';
        },
        exposer: function(ticketId) {
            return '/ticket/' + ticketId + '/exposer';
        },
        execution: function(ticketId, md5) {
            return '/ticket/' + ticketId + '/' + md5 + '/execution';
        },
        pollingUrl: function(ticketId) {
            return '/ticket/' + ticketId + '/polling';
        }
    },
    // 详情页初始化
    init: function(params) {
        // 判断用户是否登录
        var userId = $.cookie('userId');

        if (!userId || grapTicketModel.isNull(userId) || userId == "null") {
            // 跳转到登录页面
            window.location.href = "/login/login?backurl=" + window.location.href;
            alert("抢票之前必须登录");
        } else {
            // 已经成功登录
            // 计时交互
            var ticketId = params['ticketId'];
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            $.get(grapTicketModel.url.now(), {}, function(result) {
                if (result && result['success']) {
                    var nowTime = result['data'];
                    // 时间判断，计时交互
                    grapTicketModel.countdown(ticketId, nowTime, startTime, endTime);
                } else {
                    console.log('error:' + result['error']);
                }
            });
        }
    },
    isNull: function(val) {
        if (!val && typeof val != "undefined" && val != 0) {
            return true;
        }
        return false;
    },
    countdown: function(ticketId, nowTime, startTime, endTime) {
        var seckillBox = $('#ticket-box');
        // 时间判断
        if (nowTime > endTime) {
            // 秒杀结束
            seckillBox.html('秒杀结束！');
        } else if (nowTime < startTime) {
            // 秒杀未开始，计时事件绑定
            var killTime = new Date(startTime + 1000);
            seckillBox.countdown(killTime, function(event) {
                var format = event.strftime('秒杀倒计时：%D天 %H时 %M分 %S秒');
                seckillBox.html(format);
                // 时间完成后回调事件
            }).on('finish.countdown', function() {
                // 执行秒杀逻辑
                grapTicketModel.grapTicket(ticketId, seckillBox);
            })
        } else {
            // 正在秒杀
            grapTicketModel.grapTicket(ticketId, seckillBox);
        }
    },

    // 获取秒杀地址， 控制实现逻辑，执行秒杀
    grapTicket: function(ticketId, node) {
        node.hide().html('<button class ="btn btn-primary btn-lg" id="killBtn">开始抢票</button>');
        $.post(grapTicketModel.url.exposer(ticketId), {}, function(result) {
            // 在回调函数中，执行交互逻辑
            if (result && result['success']) {
                var exposer = result['data'];
                if (exposer['exposed']) {
                    // 获取秒杀地址
                    var md5 = exposer['md5'];
                    var killurl = grapTicketModel.url.execution(ticketId, md5);
                    console.log("killurl:" + killurl);

                    // 绑定一次点击事件，防止多次点击
                    $('#killBtn').one('click', function() {
                        // 执行秒杀请求
                        // 1.先禁用按钮
                        $(this).addClass('disabled');
                        // 2,发送秒杀请求,执行秒杀
                        $.post(killurl, {}, function(result) {
                            if (result && result['success']) {
                                // 判断是否加入队列
                                // 显示弹出层
                                var grapTicketStatus = $('#grapTicketStatus');
                                grapTicketStatus.modal({
                                    // 显示弹出层
                                    show: true,
                                    backdrop: 'static', // 禁止位置关闭
                                    keyboard: false // 关闭键盘事件
                                });
                                // 设置轮询查询
                                grapTicketModel.intervalId = setInterval(grapTicketModel.polling(ticketId), 1000);
                                // 显示轮询结果
                                $('#status').hide().html('<span class="label label-success">' + '正在排队中请等候...' + '</span>').show();
                            } else {
                                console.log('result:' + result['error']);
                            }
                        });
                    });
                    node.show();
                } else {
                    // 客户端时间与服务器时间差异
                    var now = exposer['now'];
                    var start = exposer['start'];
                    var end = exposer['end'];
                    // 重新开始倒计时
                    grapTicketModel.countdown(now, start, end);
                }
            } else {
                console.log('result:' + result['error']);
            }
        });
    },
    polling: function(ticketId) {
        $.post(grapTicketModel.url.pollingUrl(ticketId), {}, function(result) {
            if (result && result['success']) {
                // 请求成功
                var status = result['data'];
                if (status == 1) {
                    // 抢票成功,显示成功状态
                    $('#status').hide().html('<span class="label label-success">' + '抢票成功，已发送邮件到你邮箱...' + '</span>').show();
                } else if (status == 0) {
                    // 电影票已售完
                    $('#status').hide().html('<span class="label label-success">' + '很遗憾，票已售完...' + '</span>').show();
                } else if (status == 2) {
                    // 仍在排队中
                    return;
                } else if(status == -1) {
                    // 重复抢票
                    $('#status').hide().html('<span class="label label-success">' + '你已经抢到票了,不要重复抢票...' + '</span>').show();
                }
                else {
                    // 抢票失败
                    $('#status').hide().html('<span class="label label-success">' + '很遗憾，抢票失败，请稍候再试...' + '</span>').show();
                }
                // 清除轮询请求
                clearInterval(grapTicketModel.intervalId);
            } else {
                console.log('result:' + result['error']);
            }
        });
    },
    intervalId: null
};
