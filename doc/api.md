接口
创建留言或者回复

localhost:8080/app/message/create2
参数

username 发布人的username

userId发布人的userId

type 发布的消息类型 0留言 1回复

pid 回复的留言的编号  默认为-1（-1为留言 其余为回复）
targetUserId 被回复的人的id
content 发布的留言或者回复的内容
```java_holder_method_tree
@RequestParam(name = "username") String  username,
@RequestParam(name = "userId") Integer  userId,
@RequestParam(name = "pid",defaultValue = "-1") Integer  pid,
@RequestParam(name = "content") String  content,
@RequestParam(name = "targetUserId",required = false) Integer  targetUserId,
@RequestParam(name = "type",defaultValue = "0" ) Integer  type) {
```
