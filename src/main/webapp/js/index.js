const quitMember = document.querySelectorAll('.quitMember');

quitMember.forEach(button => {
    button.onclick = () => {
        Swal.fire({
            title: '정말로 탈퇴하시겠습니까?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '탈퇴하기',
            cancelButtonText: '아니요'
        }).then((result) => {
            if (result.isConfirmed) {
                Swal.fire(
                    '탈퇴되었습니다!'
                );                
       		window.location = '/mall/index?quit';
            };
        });
    };
});