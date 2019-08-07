function modificar(path, title, id){
       $.post(path,{"id":id, "title": title},function(responseText) {
        $('#ModalBody').html(responseText);
        $('#exampleModalLabel').html(title);
        });
}

function hacerlist(path){
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
     $.get('/modificar',{"Documento":response},function(responseText) {
        $('#Principal').html(responseText);
        });
    
}

function eliminar(){
    $.get('/eliminar',function(responseText) {
        $('#Lista').html(responseText);
        $('#Principal').html("");
        });
}
      
      


