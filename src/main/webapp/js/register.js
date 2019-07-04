/**
 * 表单校验：
 * 1. 用户名：单词字符，8到20位
 * 2. 密码：单词字符，8到20位
 * 3. email：邮箱格式
 * 4. 姓名：非空
 * 5. 手机号：手机号格式
 * 6. 出生日期：非空
 * 7. 验证码：非空
 */
'use strict';

const $errorMsg = $("#errorMsg");
const $inputUsername = $("#username");
const $inputPassword = $("#password");
const $inputEmail = $("#email");
const $inputName = $("#name");
const $inputTelephone = $("#telephone");
const $inputBirthday = $("#birthday");
const $inputCheck = $("#check");
const $imgCheckCode = $("#checkCodeImg");

/**
 * 校验用户名
 */
function checkUsername() {
    let reg = /^\w{8,20}$/;
    let match = reg.test($inputUsername.val());
    if (match) {
        $inputUsername.css("border", "");
        $errorMsg.html("");
    } else {
        $inputUsername.css("border", "1px solid red");
        $errorMsg.html("用户名为8~20个字符");
    }
    return match;
}

/**
 * 校验密码
 */
function checkPassword() {
    let reg = /^\w{8,20}/;
    let match = reg.test($inputPassword.val());
    if (match) {
        $inputPassword.css("border", "");
        $errorMsg.html("");
    } else {
        $inputPassword.css("border", "1px solid red");
        $errorMsg.html("密码为8~20个字符");
    }
    return match;
}

/**
 * 校验邮箱
 */
function checkEmail() {
    let reg = /^\w+@\w+\.\w+$/;
    let match = reg.test($inputEmail.val());
    if (match) {
        $inputEmail.css("border", "");
        $errorMsg.html("");
    } else {
        $inputEmail.css("border", "1px solid red");
        $errorMsg.html("邮箱格式不正确");
    }
    return match;
}

function checkName() {
    let isBlank = $inputName.val() === null || $inputName.val() === "";
    if (!isBlank) {
        $inputName.css("border", "");
        $errorMsg.html("");
    } else {
        $inputName.css("border", "1px solid red");
        $errorMsg.html("姓名不能为空");
    }
    return !isBlank;
}

function checkTelephone() {
    let reg = /^(13[0-9]|14[57]|15[012356789]|18[0|12356789])\d{8}$/;
    let match = reg.test($inputTelephone.val());
    if (match) {
        $inputTelephone.css("border", "");
        $errorMsg.html("");
    } else {
        $inputTelephone.css("border", "1px solid red");
        $errorMsg.html("手机号有误");
    }
    return match;
}

function checkBirthday() {
    let isBlank = $inputBirthday.val() === null || $inputBirthday.val() === "";
    if (!isBlank) {
        $inputBirthday.css("border", "");
        $errorMsg.html("");
    } else {
        $inputBirthday.css("border", "1px solid red");
        $errorMsg.html("出生日期不能为空");
    }
    return !isBlank;
}

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
    //表单提交时，调用所有的校验方法
    $("#registerForm").submit(function () {
        $errorMsg.html("");
        if (checkUsername() && checkPassword() && checkEmail() && checkName() && checkTelephone() && checkBirthday() && checkCheckCode()) {
            $("#msg").html("");
            $.post("user/register", $(this).serialize(), function (data) {
                if (data.success) {
                    window.location.href = "register_ok.html";
                } else {
                    $("#msg").html(data.errorMsg);
                }
            })
        }
        $imgCheckCode.prop("src", "check-code?" + new Date().getTime());
        //阻止页面跳转
        return false;
    });

    //失去焦点时校验
    $inputUsername.blur(checkUsername);
    $inputPassword.blur(checkPassword);
    $inputEmail.blur(checkEmail);
    $inputBirthday.blur(checkBirthday);
    $inputCheck.blur(checkCheckCode);
    $inputTelephone.blur(checkTelephone);
    $inputName.blur(checkName);

    //验证码图片展示
    $imgCheckCode.prop("src", "check-code");
    $imgCheckCode.click(function () {
        $(this).prop("src", "check-code?" + new Date().getTime());
    })
});



