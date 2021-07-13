let selectSearchDanhMuc, selectSearchSapXep, textSearchTen, numberSearchGia, numberSearchDaBan, dateSearchNgayTao,
    selectSearchConHang, btnTimKiem, tableDuLieu, textTen, selectDanhMuc, numberGia, numberDaBan, numberBaoHanh,
    numberKhuyenMai, fileAnh, dateNgayTao, textareaGioiThieu, textareaThongSo, checkboxHetHang, btnLuuLai,
    btnXacNhanXoa, btnThem;
let indexProduct, elementProduct;
let listProduct = [
    {
        id: 1,
        name: "Iphone 12",
        price: 10000,
        createDate: "2020-08-20",
        image: "https://cdn.cellphones.com.vn/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/i/p/iphone11-purple-select-2019.png",
        introduction: "Iphone tai thỏ",
        specification: "Chip A11",
        soldOut: true,
        guarantee: 12,
        categoryId: 1,
        bouth: 1000,
        promotion: 10
    },
    {
        id: 2,
        name: "Iphone 11",
        price: 10000,
        createDate: "2020-08-20",
        image: "https://cdn.cellphones.com.vn/media/catalog/product/cache/1/image/1000x/040ec09b1e35df139433887a97daa66f/i/p/iphone11-green-select-2019.png",
        introduction: "Iphone tai thỏ",
        specification: "Chip A11",
        soldOut: false,
        guarantee: 12,
        categoryId: 2,
        bouth: 1000,
        promotion: 10
    },
];

$(async function (){
    selectSearchDanhMuc = $("#select-search-danh-muc");
    selectSearchSapXep = $("#select-search-sap-xep");
    textSearchTen = $("#text-search-ten");
    numberSearchGia = $("#number-search-gia");
    numberSearchDaBan = $("#number-search-da-ban");
    dateSearchNgayTao = $("#date-search-ngay-tao");
    selectSearchConHang = $("#select-search-con-hang");
    btnTimKiem = $("#btn-tim-kiem");
    tableDuLieu = $("tbody");
    textTen = $("#text-ten");
    selectDanhMuc = $("#select-danh-muc");
    numberGia = $("#number-gia");
    numberDaBan = $("#number-da-ban");
    numberBaoHanh = $("#number-bao-hanh");
    numberKhuyenMai = $("#number-khuyen-mai");
    fileAnh = $("#file-anh");
    dateNgayTao = $("#date-ngay-tao");
    textareaGioiThieu = $("#textarea-gioi-thieu");
    textareaThongSo = $("#textarea-thong-so");
    checkboxHetHang = $("#checkbox-het-hang");
    btnLuuLai = $("#btn-luu-lai");
    btnXacNhanXoa = $("#btn-xac-nhan-xoa");
    btnThem = $("#btn-them");
    viewDanhSachSanPham();
    searchSanPham();
    xacNhanXoaSanPHam();
    luuSanPham();
    themSanPham();

});

function viewDanhSachSanPham(){
    let view = "<tr><td colspan='8'><strong>Khong co du lieu</strong></td></tr>";
    if(listProduct && listProduct.length > 0){
        view = listProduct.map((data, index)=>{
            return `<tr data-index = "${index}">
                        <th scope="row">${index + 1}</th>
                        <td><img src="${viewField(data.image)}"
                                 alt="" width="80px"></td>
                        <td>${viewField(data.name)}</td>
                        <td>${viewField(data.price)}</td>
                        <td>${viewField(data.bouth)}</td>
                        <td>${viewField(data.createDate)}</td>
                        <td class="text-center">${data.soldOut ? `<span class="badge badge-danger"> Hết hàng</span>` : `<span class="badge badge-success">Còn hàng</span>`}</td>
                        <td>
                            <button type="button" class="btn btn-warning sua-san-pham"><i class="fas fa-pen"></i>
                                Sửa</button>
                            <button type="button" class="btn btn-danger xoa-san-pham"><i class="fas fa-trash-alt"></i>
                                Xóa</button>
                        </td>
                     </tr>`
        }).join("");
    }
    tableDuLieu.html(view);
    xoaSanPham();
    suaSanPham();
}

function searchSanPham(){
    btnTimKiem.click(function (){
        let valSearchTen = textSearchTen.val();
        let valSearchGia = numberSearchGia.val();
        valSearchGia = valSearchGia.length > 0 ? valSearchGia : -1;
        let valSearchDaBan = numberSearchDaBan.val();
        valSearchDaBan = valSearchDaBan.length > 0 ? valSearchDaBan : -1;
        let valSearchNgayTao = dateSearchNgayTao.val();
        valSearchNgayTao = valSearchNgayTao.length > 0 ? valSearchNgayTao : null;
        let valSearchConHang = selectSearchConHang.val();
        listProduct = [];
        viewDanhSachSanPham();
    });
}
function xoaSanPham(){
    $(".xoa-san-pham").click(function (){
       indexProduct = $(this).parents("tr").attr("data-index");
       $("#exampleModal1").modal("show");
    });
}
function xacNhanXoaSanPHam(){
    btnXacNhanXoa.click(function (){
        console.log(indexProduct - 0);
        let idProduct = listProduct[indexProduct - 0].id;
        listProduct = listProduct.filter((data, index)=>{
            return index != indexProduct;
        });
        console.log(idProduct);
        viewDanhSachSanPham();
        $("#exampleModal1").modal("hide");
    });
}

function suaSanPham(){
    $(".sua-san-pham").click(function (){
        elementProduct = listProduct[$(this).parents("tr").attr("data-index") - 0];
        textTen.val(elementProduct.name);
        selectDanhMuc.val(elementProduct.categoryId);
        numberGia.val(elementProduct.price);
        numberDaBan.val(elementProduct.bouth);
        numberBaoHanh.val(elementProduct.guarantee);
        numberKhuyenMai.val(elementProduct.promotion);
        dateNgayTao.val(elementProduct.createDate);
        textareaGioiThieu.val(elementProduct.introduction);
        textareaThongSo.val(elementProduct.specification);
        checkboxHetHang.prop("checked", elementProduct.soldOut);
        $("#exampleModal").modal("show");
    })
}
async function checkData(selector, textError, data){
    let val = selector.val();
    let check = false;
    if(val.length > 0){
        check = true;
        hiddenError(selector);
    }else{
        viewError(selector, textError);
    }
    data = {val, check};
    return data;

}

 function luuSanPham(){
    let data = "";
    btnLuuLai.click(async function (){
        let dataObject =  await checkData(textTen, "Định dạng tên chưa đúng", data);
        let valTen = dataObject.val;
        let checkTen = dataObject.check;
        let valDanhMuc = selectDanhMuc.val();
        dataObject = await checkData(numberGia, "Giá bán phải là số",data);
        let valGia = dataObject.val;
        let checkGia = dataObject.check;
        dataObject = await checkData(numberDaBan, "Nhập số lượng đã bán ", data);
        let valDaBan = dataObject.val;
        let checkDaBan = dataObject.check;
        dataObject = await checkData(numberBaoHanh, "Nhập thời gian bảo hành", data);
        let valBaoHanh = dataObject.val;
        let checkBaoHanh = dataObject.check;
        dataObject = await checkData(numberKhuyenMai, "Nhập phần trăm khuyến mại", data);
        let valKhuyenMai = dataObject.val;
        let checkKhuyenMai = dataObject.check;
        let valGioiThieu = textareaGioiThieu.val();
        let valThongSo = textareaThongSo.val();
        let valHetHang = checkboxHetHang.is(":checked");
        if(checkTen && checkGia && checkDaBan && checkBaoHanh && checkKhuyenMai) {
            let checkAction = false;
            if(elementProduct){
                checkAction = true;
            }else{
                elementProduct = {};
            }
            elementProduct.name = valTen;
            elementProduct.categoryId = valDanhMuc;
            elementProduct.price = valGia;
            elementProduct.bouth = valDaBan;
            elementProduct.guarantee = valBaoHanh;
            elementProduct.promotion = valKhuyenMai;
            elementProduct.introduction = valGioiThieu;
            elementProduct.specification = valThongSo;
            elementProduct.soldOut = valHetHang;
            if(checkAction){
                listProduct[indexProduct] = elementProduct;
                console.log("save");
            }else{
                listProduct.push(elementProduct);
            }
            viewDanhSachSanPham();
            $("#exampleModal").modal("hide");

       }

    })
}
function themSanPham(){
    btnThem.click(function (){
        elementProduct = null;
        $("#exampleModal").modal("show");

    })
}