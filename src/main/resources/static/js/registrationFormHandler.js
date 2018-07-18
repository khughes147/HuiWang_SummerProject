jQuery(document).ready(function ($) {
	$('.fadeshop').hover(
		function(){
			$(this).find('.captionshop').fadeIn(150);
		},
		function(){
			$(this).find('.captionshop').fadeOut(150);
		}
	);
});

$('#myModal').on('shown.bs.modal', function () {
  $('#myInput').focus();
})

$('myModal').show().on('hidden', function() {

    $('myModal').modal('hide');
    $('emailForm').get(0).reset();
    $('#uniID').value = "";
});

function sendEmail()
{



        $.ajax(
        {
            type: "POST",
            data: $("#emailForm").serialize(),
            cache: false,
            url: "/emailSubmission",

            success: function(data)
            {
                $('#SuccessModal').modal('show')
            },
            error: function(data)
            {
                $('#ErrorModal').modal('show')
            }

        });
}


$(document).ready(function(){
//Write on Form
	$('#uniID').blur(function(event) {
		var input=$(this);
		var is_name=input.val();
		if(is_name){input.removeClass("is-invalid").addClass("is-valid"),
		$('p.uniErrorMessage').remove();}
		else{input.removeClass("is-valid").addClass("is-invalid"),
		$('p.uniErrorMessage').remove(),
		$('#uniID').after('<p class="uniErrorMessage">University name can\'t be empty.</p>');}
	});
	//check to make sure its an email
	//if @ not included it wont allow the form to be submitted.
	$('#emailID').blur(function(event) {
		var input=$(this);
		var re = /^[\w]+[\w.%+-]*@[\w.-]+\.ac\.uk$/;
		var is_email=re.test(input.val());
		if(is_email){input.removeClass("is-invalid").addClass("is-valid"),
		$('p.emailErrorMessage').remove();}
		else{input.removeClass("is-valid").addClass("is-invalid"),
		$('p.emailErrorMessage').remove(),
		$('#emailID').after('<p class="emailErrorMessage">Email must be an .ac.uk address.</p>');}
	});
	$('#adminID').blur(function(event) {
		var input=$(this);
		var message=$(this).val();
		if(message){input.removeClass("is-invalid").addClass("is-valid"),
		$('p.adminErrorMessage').remove();}
		else{input.removeClass("is-valid").addClass("is-invalid"),
		$('p.adminErrorMessage').remove(),
		$('#adminID').after('<p class="adminErrorMessage">Admin name can\'t be empty.</p>');}
	});
	$('#adminContactNumber').blur(function(event) {
		var input=$(this);
		var message=$(this).val();
		if(message){input.removeClass("is-invalid").addClass("is-valid"),
		$('p.numberErrorMessage').remove();}
		else{input.removeClass("is-valid").addClass("is-invalid"),
		$('p.numberErrorMessage').remove(),
		$('#adminContactNumber').after('<p class="numberErrorMessage">Contact number can\'t be empty.</p>');}
	});

//submit form validation
$("#submitButton").click(function(event){
	var form_data=$("#emailForm").serializeArray();
	var error_free=true;


    if ($('#adminID').hasClass('form-control is-valid')){
        if ($('#uniID').hasClass('form-control is-valid')){
            if ($('#emailID').hasClass('form-control is-valid')){
            sendEmail();
            }else{
            alert("Please ensure all fields are completed correctly."),
            event.stopPropagation();
            event.preventDefault();}
        }else{
        alert("Please ensure all fields are completed correctly."),
        event.stopPropagation();
        event.preventDefault();}
    }else{
    alert("Please ensure all fields are completed correctly."),
    event.stopPropagation();
    event.preventDefault();};

});

$("#submitResetButton").click(function(event){
	sendReset();
	});

});