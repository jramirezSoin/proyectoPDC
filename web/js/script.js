function limpiar(){
    $('#ModalBody').html("");
    $('#exampleModalLabel').html("");
    $('#addModalBody').html("");
    $('#addModalLabel').html("");
}

function modificar(path, title, id){
        limpiar();
       $.post(path,{"id":id, "title": title},function(responseText) {
        $('#ModalBody').html(responseText);
        $('#exampleModalLabel').html(title);
        });
}

function agregar(path, id){
        limpiar();
        $.post(path,{"id": id , "title": "add"},function(responseText) {
        $('#addModalBody').html(responseText);
        $('#addModalLabel').html(title);
        });
}

function eliminar(path, id){
        limpiar();
        $.post(path,{"id": id , "title": "del"},function(responseText) {
        });
}



function hacerlist(path){
       $('#Infomessage').text("Aloha");
       $.get(path,function(responseText) {
        $('#Lista').html(responseText);
        });
      }
      
function hacerClick(path,id){
       $.get(path,{"id":id},function(responseText) {
        $('#Principal').html(responseText);
        });
      }
      
function buscar(id, tipo){
    element = document.getElementById(id);
    $.get('/buscar',{"buscar":element.value, "tipo":tipo},function(responseText) {
        $('#'+tipo).html(responseText);
    });
}

var response = "";


function recorrerR(documento){
    var childs = documento.childNodes;
    var div = false;
    for (let i = 0; i < childs.length; i++) {
        if(childs[i].nodeName != "#text"){
            if(childs[i].id.includes("-")){
            if(childs[i].nodeName=="DIV"){
                response+=childs[i].id.substr(1)+"\n";
                recorrerR(childs[i]);
            }else{ 
                response+=childs[i].id.substr(1)+": "+childs[i].value+"\n";
                recorrerR(childs[i]);
            }}else{
                recorrerR(childs[i]);
            }
        }
    }
}

function recorrer(){
    response="";
    var documento = document.getElementById("formulaire");
    var childs = documento.childNodes;
    for (let i = 0; i < childs.length; i++) {
        if(childs[i].nodeName != "#text"){
            if(childs[i].id.includes("-")){
            if(childs[i].nodeName=="DIV"){
                response+=childs[i].id.substr(1)+"\n";
                recorrerR(childs[i]);
            }else{ 
                response+=childs[i].id.substr(1)+": "+childs[i].value+"\n";
                recorrerR(childs[i]);
            }}else{
                recorrerR(childs[i]);
            }
        }
    }
    console.log(response);
    return response;
    
}

function update(){
    var response= recorrer();
         $.get('/modificar',{"Documento":response},function(responseText) {
        $('#Principal').html(responseText);
        });
}

function add(){
    var response= recorrer();
         $.get('/guardar',{"Documento":response},function(responseText) {
        $('#Lista').html(responseText);
        });
}

function remove(){
    
    $.get('/eliminar',function(responseText) {
        $('#Principal').html(responseText);
        });
}
