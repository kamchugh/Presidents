var baseURL = window.location.protocol + "//" + window.location.host + "/" + window.location.pathname;


console.log($('goParty'));
$('#goParty').click(function() {
	window.location='baseURL + #party';
	console.log($('window.location'));
});


$('#slideshow form table tbody tr td button').click(function() {
  window.location='baseURL + #slideshow';
});