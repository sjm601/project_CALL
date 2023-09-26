const sendMessageBtn = document.getElementById('send-message-btn');
const radios = document.querySelectorAll([type = "radio"]);
const value1radios = document.querySelectorAll([value = "1"]);

radios.forEach(btn => {
	btn.onclick = () => {
		value1radios.forEach(radio => {
			radio.removeAttribute("checked");
		});
	};
});

sendMessageBtn.addEventListener('click', function() {
	//작성후 초기화
	document.getElementById('review-form').reset();
});