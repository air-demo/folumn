/*
提交回答
 */
function comment1target() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    post(questionId, 1, content);
}
/*
提交评论
 */
function comment2target(e) {
    var commentId = e.getAttribute("data-id");
    var content = $("#input-" + commentId).val();
    post(commentId, 2, content);
}
/*
通用提交方法
 */
function post(targetId, type, content) {

    if(!content){
        alert("不能回复空内容");
        return;
    }
    $.ajax({
       type:"POST",
       url:"/comment",
       data:{
           "parentId":targetId,
           "content":content,
           "type":type
       },
       success:function (response) {
           if(response.code == 200){
               window.location.reload();
           }else {
               if(response.code==2021){
                   var isAccepted=confirm(response.message);
                   if(isAccepted){
                       window.open("https://github.com/login/oauth/authorize?client_id=b259565aaf07e83a0fa4&redirect_uri=http://112.125.95.30:8081/callback&scope=user&state=1");
                       window.localStorage.setItem("closable",true);
                   }
               }else {
                   alert(response.message);
               }
           }

       },
       dataType:"json"
    });
}



/*
展开二级评论
 */
function collapseComments(e) {
    var id = e.getAttribute("data-id");
    var comments = $("#comment-" + id);

    // 获取一下二级评论的展开状态
    var collapse = e.getAttribute("data-collapse");
    if (collapse) {
        // 折叠二级评论
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    } else {
        var subCommentContainer = $("#comment-" + id);
        if (subCommentContainer.children().length != 1) {
            //展开二级评论
            comments.addClass("in");
            // 标记二级评论展开状态
            e.setAttribute("data-collapse", "in");
            e.classList.add("active");
        } else {
            $.getJSON("/comment/" + id, function (data) {
                $.each(data.data.reverse(), function (index, comment) {
                    var mediaLeftElement = $("<div/>", {
                        "class": "media-left"
                    }).append($("<img/>", {
                        "class": "media-object img-rounded",
                        "src": comment.user.avatarUrl
                    }));

                    var mediaBodyElement = $("<div/>", {
                        "class": "media-body"
                    }).append($("<h5/>", {
                        "class": "media-heading",
                        "html": comment.user.name
                    })).append($("<div/>", {
                        "html": comment.content
                    })).append($("<div/>", {
                        "class": "menu"
                    }).append($("<span/>", {
                        "class": "pull-right",
                        "html": moment(comment.gmtCreate).format('YYYY-MM-DD')
                    })));

                    var mediaElement = $("<div/>", {
                        "class": "media"
                    }).append(mediaLeftElement).append(mediaBodyElement);

                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments"
                    }).append(mediaElement);

                    subCommentContainer.prepend(commentElement);
                });
                //展开二级评论
                comments.addClass("in");
                // 标记二级评论展开状态
                e.setAttribute("data-collapse", "in");
                e.classList.add("active");
            });
        }
    }
}

function showSelectTag() {
    $("#select-tag").show();
}

function selectTag(e) {
    var value = e.getAttribute("data-tag");
    var previous = $("#tag").val();
    if (previous.indexOf(value) == -1) {
        if (previous) {
            $("#tag").val(previous + ',' + value);
        } else {
            $("#tag").val(value);
        }
    }
}

function like_count(e) {
    var commentId = e.getAttribute("data-id");
    $.ajax({
        type:"GET",
        url:"/likecomment",
        data: ({
           "id":commentId
        }),
        success: function (response) {
            if (response.code == 200) {
                window.location.reload();
            }
        },
        dataType:"json"
    });
}