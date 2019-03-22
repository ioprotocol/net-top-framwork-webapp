function ajaxPost(url, parJson, result, error) {
    $.ajax({
        type : "post",
        async : false,
        url : url,
        data : parJson,
        error: error,
        success : result
    });
}

/**
 * 填充avalon 的model对象
 */
function fillModel(msg, model) {
    if(msg != null && msg.length > 0) {
        var domain = eval("(" + msg + ")");
        var newData = avalon.mix(true, {}, model.$model, domain)
        for (var i in newData) {
            if (model.hasOwnProperty(i) && i !== "hasOwnProperty"){//安全更新数据
                model[i] = newData[i]
            }
        }
    }
}
