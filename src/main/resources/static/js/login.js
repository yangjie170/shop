function login(userName, password) {
    $.ajax({
        type: "POST",
        data: {'userName': userName, 'password': password},
        url: "/login",
        success: function (data) {
            if (data.code == 200) {
                window.location.href = "/main";
            } else {
                alert("用户名或密码错误");
            }
        },
        error: function (error) {
            alert("请求失败");
        }
    });
}