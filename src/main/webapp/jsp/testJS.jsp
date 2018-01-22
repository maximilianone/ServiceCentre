<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<head>

<script type="text/javascript" src="//code.jquery.com/jquery-1.8.3.js"></script>

<script type='text/javascript'>
    $(window).load(function(){

    $('#test').keyup(validateTextarea);

    function validateTextarea() {
            var errorMsg = "Invalid symbols";
            var textarea = this;
            var pattern = new RegExp('^' + $(textarea).attr('pattern') + '$');
            // check each line of text
            $.each($(this).val().split("\n"), function () {
                // check if the line matches the pattern
                var hasError = !this.match(pattern);
                if (typeof textarea.setCustomValidity === 'function') {
                    textarea.setCustomValidity(hasError ? errorMsg : '');
                } else {
                    // Not supported by the browser, fallback to manual error display...
                    $(textarea).toggleClass('error', !!hasError);
                    $(textarea).toggleClass('ok', !hasError);
                    if (hasError) {
                        $(textarea).attr('title', errorMsg);
                    } else {
                        $(textarea).removeAttr('title');
                    }
                }
                return !hasError;
            });
        }
    });
</script>
</head>