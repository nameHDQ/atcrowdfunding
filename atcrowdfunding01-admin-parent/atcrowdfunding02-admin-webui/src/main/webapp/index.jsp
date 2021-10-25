<%--
  Created by IntelliJ IDEA.
  User: 17583
  Date: 2021/10/18
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <script src="layer/layer.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function (){

            $("#btn04").click(function (){
                layer.msg("layer的弹框");
            });
            $("#btn02").click(function (){
                // 准备要发送的数据
                var student = {
                    stuId: 5,
                    stuNmae: "tom",
                    address: {
                        province: "江苏",
                        city: "南京",
                        street: "秣陵街道"
                    },
                    subjectList: [
                        {
                            subName: "java",
                            subScore: 100
                        },
                        {
                            subName: "c++",
                            subScore: 98
                        }
                    ],
                    map:{
                        key1: "value1",
                        key2: "value2"
                    }
                };
                var stu = JSON.stringify(student);
                $.ajax({
                    url: "send/compose/object.do",
                    type: "post",
                    data: stu,
                    //明确为json类型 必加
                    contentType: "application/json;character=UTF-8",
                    dataType: "json",
                    success: function (response){
                        alert(response.result);
                    },
                    error: function (response){
                        alert(response);
                    }
                })
            })



            $("#btn01").click(function (){
                var array = [2,3,5];
                console.log(array);
                var dataA = JSON.stringify(array);
                console.log(dataA);
                $.ajax({
                    url: "send/array1.do",
                    type: "post",
                    data: dataA,
                    //明确为json类型 必加
                    contentType: "application/json;character=UTF-8",
                    dataType: "text",
                    success: function (response){
                        alert(response);
                    },
                    error: function (response){
                        alert(response);
                    }
                });
            });
        })
    </script>
</head>
<body>
<h1>测试环境</h1>
<button id="btn01">Send text</button>
<button id="btn02">Send 对象</button>
<button id="btn04">layer</button>
</body>
</html>
