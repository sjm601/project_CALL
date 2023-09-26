const plusCountButton = document.querySelector('.cnt-plus');
const minusCountButton = document.querySelector('.cnt-minus');
const input = document.querySelector('.cnt-count');
const purchaseBtn = document.querySelector('#purchaseBtn');
const cartBtn = document.querySelector('#cartBtn');
const purchaseInput = document.querySelector('#purchase');
const form = document.querySelector('#detial-form');

plusCountButton.onclick = () => {
    if (input.value < 9) {
        input.value++;
    }
};

minusCountButton.onclick = () => {
    if (input.value > 1 && input.value < 10) {
        input.value--;
    }
};

purchaseBtn.onclick = () => {
	purchaseInput.value = "purchase";
	form.submit();
};

cartBtn.onclick = () => {
	let input = document.createElement('input');
	input.setAttribute("name", "move");
	purchaseInput.appendChild(input);
	let move = confirm("장바구니 화면으로 이동하시겠습니까?");
	if (move) {
		input.value = "move";
		form.submit();
	} else {
		input.value = "no";
		form.submit();
	}
};