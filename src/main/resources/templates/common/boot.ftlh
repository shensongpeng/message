<html>
<#include "./header.ftlh">

<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<body>

<script type="text/javascript">
    $(document).ready(function () {
        $("[name='showEditor']").click(function () {
            $(this).hide();
        });
        $("button.btn-sm").click(function () {

            const a = $(this).parent().next();
            $(a).toggle();

        })

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
        form.action = "http://localhost:8080/app/message/create";
        form.submit();
    }
    function resetView() {
        const t = document.getElementsByName("showEditor");
        $("#contentDiv").hide();
        $("[name='showEditor']").show();
    }
    function sendReply() {
        $.post('http://localhost:8080/app/message/create')
    }
</script>

<div class="container">


<form role="form"  id="sendMessageForm" method="post" onsubmit="sendMessage()">
    <div class="form-group "  >
        <div  id="contentDiv" >
            <input class="hidden" name="username" value="漩涡鸣人">
            <input class="hidden" name="userId" value="3">
            <textarea rows="4" class="form-control " id="messageContent" name="content">

            </textarea>
            <button type="submit" onclick="sendMessae()" class="btn btn-default">发表</button>
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
                                <span>${messageDTO.createTime}   </span>
                                <button type="button" class="btn btn-default btn-sm">
                                    <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
                                    赞
                                </button>
                                <button type="button" class="btn btn-default btn-sm">
                                    <span class="glyphicon glyphicon-comment" aria-hidden="true"></span>
                                    评论
                                </button>
                            </h5>
                            <div style="display:none">
                                <form role="form" name="sendReply" action="http://localhost:8080/app/message/create" method="post">
                                    <input type="hidden" name="username" value="漩涡鸣人">
                                    <input type="hidden" name="userId" value="3">
                                    <input type="hidden" name="pid" value="${messageDTO.id}">
                                    <input type="hidden" name="type" value="1">
                                    <input type="hidden" name="targetUserId" value="${messageDTO.userId}">
                                    <textarea rows="4" class="form-control " name="content"></textarea>
                                    <button type="submit" class="btn btn-default">发表</button>
                                    <button type="reset" class="btn btn-default">取消</button>
                                </form>
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
                                                <button type="button" class="btn btn-default btn-sm">
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
</div>
</body>

</html>