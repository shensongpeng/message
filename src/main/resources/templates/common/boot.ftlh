<html>
<#include "./header.ftlh">
<body>

<ul>
<#--    <#list messageDTOS as messageDTO>-->

<#--        <div><span>${messageDTO.userName}</span>-->
<#--        </div>-->
<#--        <div class="list-group">-->
<#--                <img class="list-group-item img-responsive" width="100" height="100" src="${messageDTO.userAvatar}">-->
<#--                <a href="#" class="list-group-item active">-->
<#--                        <h4 class="list-group-item-heading">List group item heading</h4>-->
<#--                        <p class="list-group-item-text">${messageDTO.content}</p>-->
<#--                </a>-->
<#--        </div>-->
<#--    </#list>-->

</ul>

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
</body>
</html>