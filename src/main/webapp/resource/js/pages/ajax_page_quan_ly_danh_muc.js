let listCategory = null;
$(async function () {
   await categoryFindAll().then(rs => {
        if (rs.message == "Success") {
            listCategory = rs.data;
        } else {
            console.log(rs.data);
        }

    }).catch(err => {
        console.log();
    })
})