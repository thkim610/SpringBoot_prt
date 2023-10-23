//블로그 글 삭제 기능
const deleteButton = document.getElementById('delete-btn');

if(deleteButton){
    //삭제 버튼 클릭 시,
    deleteButton.addEventListener('click', event=>{
        let id = document.getElementById('article-id').value;
        //삭제 API요청(fetch/DELETE)
        fetch(`/api/articles/${id}`, {
            method: 'DELETE'
        })
            .then(()=>{
                alert('삭제가 완료되었습니다.'); //삭제가 실행되면, 알림창 띄움.
                location.replace('/articles'); //사용자의 웹 브라우저 화면을 현재 주소를 기반해 옮겨줌.
            });
    });
}

//블로그 글 수정 기능
const modifyButton = document.getElementById('modify-btn');

if(modifyButton){
    // 클릭 이벤트가 감지되면 수정 API 요청
    modifyButton.addEventListener('click', event=>{
        let params = new URLSearchParams(location.search);
        let id = params.get('id'); //파라미터에서 id 값을 id에 할당.
        //수정API요청 (fetch/PUT) => JSON 방식으로 통신
        fetch(`/api/articles/${id}`,{
            method : 'PUT',
            headers : {
                "Content-Type" : "application/json", //json 형식으로 데이터 전송
            },
            body : JSON.stringify({ //json 형식으로 파라미터의 값을 서버로 전송한다.
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        })
            .then(()=>{
                alert('수정이 완료되었습니다.'); //요청 처리 후 완료 응답으로 알림창 띄움.
                location.replace(`/articles/${id}`); //해당 id의 블로그 글로 이동.
            });
    });

}