const deleteButtons = document.querySelectorAll('.deleteBtn');
const num = document.querySelectorAll('.cartListNumber');
deleteButtons.forEach((button, idx) => {
    button.onclick = () => {
        Swal.fire({
            title: '삭제하시겠습니까?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '네',
            cancelButtonText: '아니요'
        }).then((result) => {
            if (result.isConfirmed) {
                Swal.fire(
                    '삭제되었습니다.'
                );
                window.location = '/mall/order/cart?delete='+num[idx].innerHTML;
            };
        });
    };
});

const plusCountButtons = document.querySelectorAll('.cnt-plus'); // plus (button)
const minusCountButtons = document.querySelectorAll('.cnt-minus'); // minus (button)
const nameDivs = document.querySelectorAll('.pname'); // productName (div)
const priceDivs = document.querySelectorAll('.pPrice'); // price (div) 
const inputs = document.querySelectorAll('.cnt-count'); // count (input)
const countPriceSpans = document.querySelectorAll('.countPrice'); // price * count (span)
const totalPriceSpan = document.querySelector('#totalPrice'); // totalPrice (span)

plusCountButtons.forEach((button, btnIdx) => {
    button.onclick = () => {
        if (inputs[btnIdx].value < 9) {
            inputs[btnIdx].value++;
            price = priceDivs[btnIdx].innerHTML.replace(/,/g, "");
            countPriceSpans[btnIdx].innerHTML = (inputs[btnIdx].value * price).toLocaleString();
            totalPrice = totalPriceSpan.innerHTML.replace(/,/g, "");
            totalPriceSpan.innerHTML = (parseInt(totalPrice) + parseInt(price)).toLocaleString();
        };
    };
});

minusCountButtons.forEach((button, btnIdx) => {
    button.onclick = () => {
        if (inputs[btnIdx].value > 1 && inputs[btnIdx].value < 10) {
            inputs[btnIdx].value--;
            price = priceDivs[btnIdx].innerHTML.replace(/,/g, "");
            countPriceSpans[btnIdx].innerHTML = (inputs[btnIdx].value * price).toLocaleString();
            totalPrice = totalPriceSpan.innerHTML.replace(/,/g, "");
            totalPriceSpan.innerHTML = (parseInt(totalPrice) - parseInt(price)).toLocaleString();
        };
    };
});