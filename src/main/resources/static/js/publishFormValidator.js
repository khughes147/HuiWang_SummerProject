$(document).ready(function(){


//Write on Form
	$('#fingerPrintID').blur(function(event) {
		var input=$(this);
		var is_name=input.val();
		if(is_name){input.removeClass("is-invalid").addClass("is-valid"),
		$('p.studentNameErrorMessage').remove();}
		else{input.removeClass("is-valid").addClass("is-invalid"),
		$('p.studentNameErrorMessage').remove(),
		$('#fingerPrintID').after('<p class="studentNameErrorMessage">Student name can\'t be empty.</p>');}
	});
	//check to make sure its an email
	//if @ not included it wont allow the form to be submitted.

	$('#producerID').blur(function(event) {
		var input=$(this);
		var message=$(this).val();
		if(message){input.removeClass("is-invalid").addClass("is-valid"),
		$('p.studentIdErrorMessage').remove();}
		else{input.removeClass("is-valid").addClass("is-invalid"),
		$('p.studentIdErrorMessage').remove(),
		$('#producerID').after('<p class="studentIdErrorMessage">Student ID can\'t be empty.</p>');}
	});



    	$('#walletPasswordID').blur(function(event) {
        		var input=$(this);
        		var message=$(this).val();
        		if(message){input.removeClass("is-invalid").addClass("is-valid"),
        		$('p.studentIdErrorMessage').remove();}
        		else{input.removeClass("is-valid").addClass("is-invalid"),
        		$('p.studentIdErrorMessage').remove(),
        		$('#walletPasswordID').after('<p class="studentIdErrorMessage">Student ID can\'t be empty.</p>');}
        	});




$("#publishButton").click(function(event){

    if ($('#fingerPrintID').hasClass('form-control is-valid')){
        if ($('#producerID').hasClass('form-control is-valid')){
                if ($('#walletPasswordID').hasClass('form-control is-valid')){

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
    event.preventDefault();}
});

$('.btn-file :file').on('fileselect', function(event, numFiles, label) {

        var input = $(this).parents('.input-group').find(':text'),
            log = numFiles > 1 ? numFiles + ' files selected' : label;

        if( input.length ) {
            input.val(log);
        } else {
            if( log ) alert(log);
        }

    });
});

$(document).on('change', '.btn-file :file', function() {
  var input = $(this),
      numFiles = input.get(0).files ? input.get(0).files.length : 1,
      label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
  input.trigger('fileselect', [numFiles, label]);
});