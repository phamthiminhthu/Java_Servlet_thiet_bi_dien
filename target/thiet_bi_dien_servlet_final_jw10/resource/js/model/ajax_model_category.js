var urlCategory = "category/";

function categoryFindAll(){
    return ajaxGet(`${urlCategory}find-all`);
}

function categoryFindById(id){
    return ajaxGet(`${urlCategory}fimd-by-id?id=${id}`);
}


