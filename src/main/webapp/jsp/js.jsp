<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<head>
<script src="/ServiceCentre/js/jquery-3.1.1.min.js"></script>
<script src="/ServiceCentre/js/jquery.maskedinput.min.js"></script>
<script src="/ServiceCentre/js/jquery.validate.js"></script>
<script type="text/javascript">
		jQuery(function($){
		   $("#phone").mask("+38 (999) 999-99-99");
		});
		</script>
<script type="text/javascript">
    $(function () {
        $("#btnSubmit").click(function () {
            var password = $("#password").val();
            var confirmPassword = $("#password_confirm").val();
            if (password != confirmPassword) {
                alert("Passwords do not match.");
                return false;
            }
            return true;
        });
    });
</script>
<script type="text/javascript">
    $(function () {
         $("#confirmBtn").click(function () {
             var r = confirm("Are you sure?");
             if (r == true) {
                 return true;
             } else {
                 return false;
             }
         });
    });
</script>
</head>
