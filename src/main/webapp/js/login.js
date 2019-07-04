'use strict';

const $inputCheck = $("#check");
const $imgCheckCode = $("#checkCodeImg");
const $btnLogin = $("#btn_sub");
const $formLogin = $("#loginForm");
const $errorMsg = $("#errorMsg");

function checkCheckCode() {
    let isBlank = $inputCheck.val() === null || $inputCheck.val() === "";
    if (!isBlank) {
        $inputCheck.css("border", "");
        $errorMsg.html("");
    } else {
        $inputCheck.css("border", "1px solid red");
        $errorMsg.html("验证码不能为空");
    }
    return !isBlank;
}

$(function () {
    $inputCheck.blur(checkCheckCode);
    //验证码图片展示
    $imgCheckCode.prop("src", "check-code");
    $imgCheckCode.click(function () {
        $(this).prop("src", "check-code?" + new Date().getTime());
    });

    $btnLogin.click(function () {
        $.post("user/login", $formLogin.serialize(), function (data) {
            if (data.success) {
                location.href = "index.html";
            } else {
                $errorMsg.html(data.errorMsg);
            }
        });
        $imgCheckCode.prop("src", "check-code?" + new Date().getTime());
    })
});