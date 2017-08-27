jQuery(document).ready(function () {

    /*
     Fullscreen background
     */
    $.backstretch("/img/1.jpg");

    /*
     Form validation
     */

    $("form").validate({
        debug:true,
        rules: {
            username: "required",
            password: "required",
            captcha: "required"
        },
        onkeyup: false,
        onclick: false,
        focusInvalid: true,
        errorClass: 'help-block',
        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error');
        },

        success: function (label) {
            // label.removeClass('input-error');
            label.closest('.form-group').removeClass('has-error');
            label.remove();
        },

        errorPlacement: function (error, element) {
            element.parent('div').append(error);
        },
        submitHandler: function (form) {
            var password = $("#password").val();
            $("#password").val(md5(password));
            form.submit();
        }
    });
});
