$(function (){
    categoryFindAll().then(rs=>{
       console.log(rs);

    }).catch(err =>{
        console.log(err);
    })
})