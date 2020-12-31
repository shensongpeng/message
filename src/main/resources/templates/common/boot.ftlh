<html>
<#include "./header.ftlh">

<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="/app/css/graph.css">
<body>

<script type="text/javascript">
    $(document).ready(function () {
        $("[name='showEditor']").click(function () {
            $(this).toggle();
        });
        $("button.btn-sm.showEdit").click(function () {
            const a = $(this).parent().next();
            $(a).toggle();
        });
        $("button.btn-sm.awe").click(function () {
            const awe = $(this).children()[0];
            $.ajax({
                type: 'POST',
                url: "/app/awe/createAwe",
                data: {
                    "messageId":awe.innerHTML,
                },
                success: function (data) {
                    console.log("点赞成功", data);
                    window.location.href = "/app/message/getMessages";
                },
                error: function (err) {

                    console.log("点赞失败", err);
                    alert("点赞失败"+err);

                }
            });

        });

    })

</script>
<script>
    function displayResult()
    {
        // alert(event.srcElement.prototype)
        $('#contentDiv').show();

        // alert(this);
    }
    function sendMessage() {
        const form = document.getElementById("sendMessageForm");
        // form.action = "http://localhost:8080/app/message/create";

        const textarea = form.elements[0];
        textarea.disable = true;
        const content = textarea.value;
        $.ajax({
            type: 'POST',
            url: "/app/message/create",
            data: {

                "content": content,
            },
            success: function (data) {
                console.log("登录成功", data);
                // window.location.href = "/app/message/getMessages";
                window.location.reload();
            },
            error: function (err) {
                textarea.disable = false;
                console.log("发布失败", err);
                alert("发布失败"+err);

            }
        });
        // form.submit();
    }
    function resetView() {
        const t = document.getElementsByName("showEditor");
        $("#contentDiv").hide();
        $("[name='showEditor']").show();
    }
    function sendReplyMessage(id) {
        let form = id.parentNode;

        console.log(form.elements);
        console.log(form.childNodes);
        console.log("123");
        form = form.children[0];
        form.action = "#";
        let childrenForm = form.children;
        let pidInput = childrenForm[0];
        let typeInput = childrenForm[1];
        let targetUserIdInput = childrenForm[2];
        let contentInput = childrenForm[3];
        let button = childrenForm[4];
        button.disable = true;

        $.ajax({
            type: 'POST',
            url: "/app/message/create",
            data: {
                content:contentInput.value,
                type:typeInput.value,
                pid:pidInput.value,
                targetUserId:targetUserIdInput.value,
            },
            success: function (data) {
                console.log("登录成功", data);
                button.disable = true;
                // window.location.href = "/app/message/getMessages";
                window.location.reload();
            },
            error: function (err) {
                textarea.disable = false;
                console.log("发布失败", err);
                button.disable = true;
                alert("发布失败"+err);

            }
        });


    }
</script>

<div class="container">


    <form role="form"  id="sendMessageForm" method="post" onsubmit="sendMessage()">
        <div class="form-group "  >
            <div  id="contentDiv"  >
                <textarea rows="4" class="form-control " id="messageContent" name="content">

                </textarea>
                <button type="submit"  class="btn btn-default">发表</button>
                <button type="reset" onclick="resetView()" class="btn btn-default">取消</button>
            </div>

        </div>
        <div>
            <button type="button" name="showEditor" onclick="displayResult()" class="btn btn-default">
                发表留言
            </button>
        </div>

    </form>
    <ul class="media-list">
            <#list messageDTOS as messageDTO>
                    <li class="media">
                            <div class="media-left ">
                                    <a href="#">
                                            <img class="media-object" width="64" height="64" src="${messageDTO.userAvatar}" alt="...">
                                    </a>
                            </div>
                            <div class="media-body">
                                <h4 class="media-heading">${messageDTO.userName}</h4>
                                ${messageDTO.content}
                                <h5>
                                    <span>${messageDTO.createTime}</span>
                                    <button type="button" class="btn btn-default btn-sm awe">
                                        <div type="hidden" style=" display:none;">${messageDTO.id}</div>
                                        <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
                                        赞
                                    </button>
                                    <button type="button" class="btn btn-default btn-sm showEdit">
                                        <span class="glyphicon glyphicon-comment" aria-hidden="true"></span>
                                        评论
                                    </button>
                                </h5>
                                <div style="display:none">
                                    <form role="form" name="sendReply" onsubmit="sendReplyMessage(this)" TARGET="targetIfr">
                                        <input type="hidden" name="pid" value="${messageDTO.id}">
                                        <input type="hidden" name="type" value="1">
                                        <input type="hidden" name="targetUserId" value="${messageDTO.userId}">
                                        <textarea rows="4" class="form-control " name="content"></textarea>
                                        <button type="submit" class="btn btn-default">发表</button>
                                        <button type="reset" class="btn btn-default">取消</button>
                                    </form>
                                    <iframe name="targetIfr" style="display:none"></iframe>
                                </div>

                                <ul class="media-list">
                                    <#list messageDTO.messageList as childMessageDTO>
                                        <li class="media">
                                            <div class="media-left">
                                                <a href="#">
                                                    <img class="media-object" width="64" height="64" src="${childMessageDTO.userAvatar}" alt="...">
                                                </a>
                                            </div>
                                            <div class="media-body">
                                                <h4 class="media-heading">${childMessageDTO.userName}</h4>
                                                ${childMessageDTO.content}
                                                <h5>
                                                    <span>${childMessageDTO.createTime}   </span>
                                                    <button type="button" class="btn btn-default btn-sm awe">
                                                        <div type="hidden" style=" display:none;">${childMessageDTO.id}</div>
                                                        <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"> </span>
                                                        赞
                                                    </button>

                                                </h5>
                                            </div>
                                        </li>
                                    </#list>
                                </ul>
                            </div>

                    </li>
            </#list>
    </ul>
    <#--分页-->
    <div class="col-md-12 column">
        <ul class="pagination pull-right">
            <#if currentPage lte 1>
                <li class="disabled"><a href="#">上一页</a></li>
            <#else>
                <li><a href="/app/message/getMessages?page=${currentPage - 1}&size=${size}">上一页</a></li>
            </#if>

            <#list 1..totalPages as index>
                <#if currentPage == index>
                    <li class="disabled"><a href="#">${index}</a></li>
                <#else>
                    <li><a href="/app/message/getMessages?page=${index}&size=${size}">${index}</a></li>
                </#if>
            </#list>

            <#if currentPage gte totalPages>
                <li class="disabled"><a href="#">下一页</a></li>
            <#else>
                <li><a href="/app/message/getMessages?page=${currentPage + 1}&size=${size}">下一页</a></li>
            </#if>
        </ul>
    </div>
</div>
</body>

</html>