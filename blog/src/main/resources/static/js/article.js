//블로그 글 삭제 기능
const deleteButton = document.getElementById('delete-btn');

if(deleteButton){
    //삭제 버튼 클릭 시,
    deleteButton.addEventListener('click', event=>{
        let id = document.getElementById('article-id').value;
        fetch(`/api/articles/${id}`, {
            method: 'DELETE'
        })
            .then(()=>{
                alert('삭제가 완료되었습니다.'); //삭제가 실행되면, 알림창 띄움.
                location.replace('/articles'); //사용자의 웹 브라우저 화면을 현재 주소를 기반해 옮겨줌.
            });
    });
}