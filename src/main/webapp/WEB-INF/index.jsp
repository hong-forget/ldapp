<%@ page import="java.util.*" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        body {
            font-size: 14px;
            font-family: "微软雅黑", Arial, helvetica, "华文黑体", "方正黑体简体";
            line-height: 1.5;

        }

        * {
            margin: 0;
            padding: 0
        }

        .header {
            width: 100%;
            height: 110px;
        }

        .zhong {
            width: 1200px;
            margin: 0 auto;
            padding: 20px;

        }

        .logo {
            width: 250px;
            float: left;
            overflow: hidden;
        }

        img {
            border: 0;
            margin: 0;
            padding: 0;
            vertical-align: middle;
        }

        ul {
            list-style: none;
            padding: 5px;

        li {

        }
    </style>
    <script type="text/javascript" src="./rujin_files/clipboard.min.js"></script>
    <script type="text/javascript" src="./rujin_files/jquery-3.4.1.min.js"></script>
    <script>
        function isRealNum(val){
            // isNaN()函数 把空串 空格 以及NUll 按照0来处理 所以先去除
            if(val === "" || val ==null){
                return false;
            }
            if(!isNaN(val)){
                return true;
            }else{
                return false;
            }
        }
        function submit() {

            var sum=$("#sum-1").val();

            var account=$("#account-1").val();

            var name=$("#name-1").val();

            // var img = document.getElementById('file').files[0];
            var img = $('#img3').attr("src");
            if(!sum||sum.length<1){
                alert("金额为必填项!");
                return;
            }else{
                if(!isRealNum(sum)){
                    alert("金额为数字!");
                    return;
                }
            }
            if(!account||account.length<1){
                alert("附言为必填项!");
                return;
            }
            if(!name||name.length<1){
                alert("您的名字为必填项!");
                return;
            }
            if(!img||img.length<100){
                alert("请上传交易单据截图!");
                return;
            }

            var sendData=new FormData();

            sendData.append("name",name);
            sendData.append("account",account);
            sendData.append("sum",sum);
            sendData.append('img',img);
            $.ajax({
                url:'http://47.96.147.91:80',
                data:sendData,
                type:'post',
                //ajax2.0可以不用设置请求头，但是jq帮我们自动设置了，这样的话需要我们自己取消掉
                contentType:false,
                //取消帮我们格式化数据，是什么就是什么
                processData:false,
                success:function(backData){
                    console.log(backData);
                }
            });

            /*$.ajax({
                type: "get",
                async: false,
                url: "http://localhost:80?data="+data,
                dataType: "jsonp",
                jsonp: "callback",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(一般默认为:callback)
                jsonpCallback:"flightHandler",//自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名，也可以写"?"，jQuery会自动为你处理数据
                processData:false,
                success: function(json){
                    alert('您查询到航班信息：票价： ' + json.price + ' 元，余票： ' + json.tickets + ' 张。');
                },
                error: function(){
                    alert('fail');
                }
            });*/

            alert('提交成功');


            location.reload();

        }
        function upload() {
            alert("1");

            var file = document.getElementById("upload");
            file.click();


        }

        function copy(id) {
            var clipboard = new ClipboardJS(id);

            clipboard.on('success', function (e) {
                console.log(e);
            });

            clipboard.on('error', function (e) {
                console.log(e);
            });
        }
    </script>
</head>
<body>
<div class="header">
    <div style="width: 1200px;height: 90px;margin: 0 auto">
        <div class="logo">
            <img src="rujin_files/logo.png">
        </div>
        <div style=" float: left;width:950px;font-size: larger;text-align: center;line-height: 90px;color: #d8ae3f;">
            <h1>入金申请</h1></div>
    </div>
    <h2 style="text-align: center;color: white;padding: 10px;background-color: #d1333a;">为了保证资金能及时到账，请在15分钟内完成转账入金。</h2>
    <div style="height: 18px;background-color: #999999"></div>
    <div class="zhong">
        <h3 style="padding: 5px 5px 25px 5px">请通过网银或手机银行实时转账到以下清算中心指定收款账户。请勿关闭浏览器以防止交易中断/失败。</h3>
        <ul>
            <li style="border: 1px solid lightblue;width: 1000px;background-color: floralwhite">
                <div style="width: 250px;text-align: center;display: inline-block;padding: 8px;border-right:1px solid lightblue">
                    收款人姓名
                </div>
                <div style="display: inline-block;padding: 8px;"><h4 id="cpy1">张先勇</h4></div>
                <div style="display: inline-block;padding: 5px;">
                    <button id="biao1"
                            style="border-radius: 5%;border: 0;padding: 2px 10px;background-color: lightblue;"
                            data-clipboard-action="copy" data-clipboard-target="#cpy1" onclick='copy("#biao1")'>复制
                    </button>
                </div>
            </li>
            <li style="border: 1px solid lightblue;border-top: 0; width: 1000px;">
                <div style="width: 250px;text-align: center;display: inline-block;padding: 8px;border-right:1px solid lightblue">
                    收款人账号
                </div>
                <div style="display: inline-block;padding: 8px;"><h4 id="cpy2">6228480819460704771</h4></div>
                <button id="biao2" style="border-radius: 5%;border: 0;padding: 2px 10px;background-color: lightblue;"
                        data-clipboard-action="copy" data-clipboard-target="#cpy2" onclick='copy("#biao2")'>复制
                </button>
            </li>
            <li style="border: 1px solid lightblue;border-top: 0;width: 1000px;background-color: floralwhite">
                <div style="width: 250px;text-align: center;display: inline-block;padding: 8px;border-right:1px solid lightblue">
                    收款银行
                </div>
                <div style="display: inline-block;padding: 8px;"><h4 id="cpy3">中国农业银行</h4></div>
                <div style="display: inline-block;padding: 5px;">
                    <button id="biao3"
                            style="border-radius: 5%;border: 0;padding: 2px 10px;background-color: lightblue;"
                            data-clipboard-action="copy" data-clipboard-target="#cpy3" onclick='copy("#biao3")'>复制
                    </button>
                </div>
            </li>
            <li style="border: 1px solid lightblue;border-top: 0; width: 1000px;">
                <div style="width: 250px;text-align: center;display: inline-block;padding: 8px;border-right:1px solid lightblue">
                    收款银行支行
                </div>
                <div style="display: inline-block;padding: 8px;"><h4 id="cpy4">湖南省嘉禾县支行珠泉分理处</h4></div>
                <button id="biao4" style="border-radius: 5%;border: 0;padding: 2px 10px;background-color: lightblue;"
                        data-clipboard-action="copy" data-clipboard-target="#cpy4" onclick='copy("#biao4")'>复制
                </button>
            </li>
        </ul>
        <div>
            <div style="width: 50%;display: inline-block;padding: 40px">
                <ul>
                    <li style="padding: 10px">
                        <div style="display: inline-block;width: 250px;text-align: center"><h4>金额<label style="color: #d1333a">*</label></h4></div>
                        <div style="display: inline-block"><input id="sum-1"
                                                                  style="border: 1px solid lightblue;padding-left: 10px;height: 30px;width: 200px;border-radius: 3%"
                                                                  type="text" placeholder="请输入您的入金金额"></div>
                    </li>
                    <li style="padding: 10px">
                        <div style="display: inline-block;width: 250px;text-align: center;"><h4>附言（备注）<label style="color: #d1333a">*</label></h4></div>
                        <div style="display: inline-block"><input id="account-1"
                                                                  style="border: 1px solid lightblue;padding-left: 10px;height: 30px;width: 200px;border-radius: 3%"
                                                                  type="text" placeholder="请输入您的MT4账号"></div>
                    </li>
                    <li style="padding: 10px">
                        <div style="display: inline-block;width: 250px;text-align: center"><h4>您的名字<label style="color: #d1333a">*</label></h4></div>
                        <div style="display: inline-block"><input id="name-1"
                                                                  style="border: 1px solid lightblue;padding-left: 10px;height: 30px;width: 200px;border-radius: 3%"
                                                                  type="text" placeholder="请输入您的姓名"></div>
                    </li>
                    <li style="padding: 10px">
                        <div style="display: inline-block;width: 250px;text-align: center;line-height: 82px"><h4>交易单据截图<label style="color: #d1333a">*</label></h4></div>
                        <div style="display: inline-block;line-height: 80px">
                            <div id="imgPreview">
                                <div id="prompt3">
                                     <span id="imgSpan">点击上传<br/>
                                        <i class="aui-iconfont aui-icon-plus"></i>
                                    <!--AUI框架中的图标-->
                                    </span>
                                        <input type="file" id="file" class="filepath" onchange="changepic(this)"
                                               accept="image/jpg,image/jpeg,image/png,image/PNG">
                                     <!--当vaule值改变时执行changepic函数，规定上传的文件只能是图片-->
                                </div>
                                <img src="#" id="img3"/>
                            </div>
                        </div>
                    </li>

                </ul>
            </div>
            <div style="width: 40%;display: inline-block;">
                <button style="border-radius: 5%;border: 0;padding: 5px 60px;background-color: #d1333a;color: white;" onclick="submit()">
                    我付了
                </button>
            </div>
        </div>
        <div style="padding-left: 5px;font-weight: bold">
            <p>注意事项</p>
            <ul style="padding-left: 15px">
                <li>1. 请在15分钟内完成转账。<label style="color: #d1333a">超过15分钟后支付，本公司不保证资金能成功到账。</label></li>
                <li>2. 转账前请仔细核对信息，务必填写“附言（备注填写MT4账号）”。<label style="color: #d1333a">如果没有准确填写“附言（备注填写MT4账号）”，本公司不保证资金能成功到账。</label></li>
                <li>3. 使用支付宝及微信转账无法保障到账时间，建议使用网银或手机银行充值入金。</li>
            </ul>

        </div>
    </div>

</div>
<div class="sidebar" style="z-index:2000;top:250px;position: fixed;
    right: 0px;">

    <div style="width: 30px;padding:5px;background-color: #d1333a;border-radius: 2%">
        <a id="hover" href="javascript:;" style="color: white;display: block;text-decoration: none">
            <ul>
                <li><h3>联</h3></li>
                <li><h3>系</h3></li>
                <li><h3>我</h3></li>
                <li><h3>们</h3></li>
            </ul>

            <div id="weix" style="position: relative;left:-210px;height: 0;top:-116px">
                <img style="width:200px;height:250px" src="./rujin_files/wechat.jpg">    <!-- 宽度长度 暂时修改 -->

            </div>
        </a>
    </div>

</div>
</body>
<style>
    #hover:hover #weix{display:block}
    #weix{ display: none}
    #imgPreview {
        width: 210px;
        height: 90px;
        margin: 10px auto 0px auto;
        border: 1px solid lightblue;
        text-align: center;
    }

    #prompt3 {
        width: 100%;
        height: 80px;
        text-align: center;
        position: relative;
    }

    #imgSpan {
        position: absolute;
        top: 10px;
        left: 75px;
        color: gray;
    }

    .filepath {
        position: absolute;
        top: 0px;
        left: 0px;
        width: 100%;
        height: 100%;
        opacity: 0;
    }

    #img3 {
        height: 100%;
        width: 100%;
        display: none;
    }
</style>
<script>
    function changepic() {
        $("#prompt3").css("display", "none");
        var reads = new FileReader();
        f = document.getElementById('file').files[0];
        reads.readAsDataURL(f);
        reads.onload = function (e) {
            document.getElementById('img3').src = this.result;
            $("#img3").css("display", "inline-block");
        };
    }
</script>
</html>

