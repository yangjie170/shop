function deletePolicy(policyId, callback) {
    layui.layer.confirm('确认删除此数据？', function(index){
        $.ajax({
            type: 'post',
            dataType: 'json',
            data: {'policyId': policyId},
            url: '/policy/delete',
            success: function (data) {
                layer.close(index);
                callback();
            }
        })
    });
}

function convertPolicy(policyId, convertType, callback) {
    layui.layer.confirm('确定要转此数据？', function(index){
        $.ajax({
            type: 'post',
            dataType: 'json',
            data: {'policyId': policyId, 'convertType': convertType},
            url: '/policy/delete',
            success: function (data) {
                layer.close(index);
                callback();
            }
        })
    });
}