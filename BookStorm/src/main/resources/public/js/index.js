$(document).ready(function(){

    triggerClick();
    triggerInput();

    function triggerInput(){
        console.log('val');

        $('#type').change(sendType);
        $('#search').on('input', search);
        $('#delete').on('input', deleteB);
        $('#getBooks').click(getBooks);
        $('#add').click(addBook);

    }

    function sendType(event){
        const val = $(event.target).val();
        console.log(val);
        fetch('/type/'+val, {
            method:'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        }).then(response =>{
            if (response.ok){
                return response.json();
            }console.log(response.message())
        }).then(data=>{
            console.log(data);
        })
    }

    function search(event){
        let input = $(event.target).val();
        if(input.length == 10){
            const id = $(event.target).attr('id');

            fetch('/'+id+'/'+input, {
                method:'GET',
                headers: {
                    'Content-Type': 'application/json',
                },
            }).then(response =>{
                    return response.json()
                
            }).then(data => {
                if (data.success == undefined ) {
                    $("#result").html(renderBook(data));
                } else {
                    $("#result").html(data.message);
                }
            })
        }
    }
    function deleteB(event){
        let input = $(event.target).val();
        if(input.length == 10){
            const form = new FormData();
            const id = $(event.target).attr('id');
            form.append("isbn", input);
            fetch('/delete', {
                method:'delete',
                body:form
            })  .then(response => {
                return response.json();
            }).then(data=>{
                    $("#result").html(data.message);
            })
        }
    }


    function getBooks(){
        fetch('/all', {
            method:'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        }).then(response =>{
            if (response.ok){
                return response.json();
            }console.log(response.message())
        }).then(data=>{
            $("#result").html('');
            for(d of data)
            $("#result").append(renderBook(d));
        })
    }
    function triggerClick(){
        $('#addBook').click(toggleAddBook);
    }

    function toggleAddBook(event){
        const pos = $(event.target).position();
        const selfWidth = $('#toggle').width(); 
        const halfSelfWidth = parseFloat(selfWidth) / 2; 
        
        $('#toggle').css({'top': pos.top + 5, 'left': pos.lef});
        console.log($('#toggle').position()) 
        $('#toggle').slideToggle(1000);
    }

    function addBook(){
        const isbn = $('#bookISBN').val();
        const title = $('#bookTitle').val();
        const author = $('#bookAuthor').val();
        const price = $('#bookPrice').val();

        if(isbn.length == 10 && title!='' && author != '' && price != ''){
            const form = new FormData();
            form.append('isbn', isbn);
            form.append('title', title);
            form.append('author', author);
            form.append('price', price);

            fetch('/add',{
                method:'POST',
                body:form
            }).then(response=>{
                return response.json();
            })
            .then(data=>{
                $("#result").html('');
                $("#result").html(data.message);

            })
        }
    }

    function renderBook(data){
    return `
        <p><strong>ISBN:</strong> ${data.isbn}
        <strong>Title:</strong> ${data.title}
        <strong>Author:</strong> ${data.auther}
        <strong>Price:</strong> ${data.price}</p>
    `;

    }
});