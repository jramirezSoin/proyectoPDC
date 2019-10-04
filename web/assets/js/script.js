function limpiar(){
    $('#ModalBody').html("");
    $('#exampleModalLabel').html("");
    $('#addModalBody').html("");
    $('#addModalLabel').html("");
}

function getChecks(id){
    var checks="";
    var elements= document.getElementsByClassName(id);
    for(var i=0; i< elements.length; i++){
        if(elements[i].checked)
            checks+=","+(elements[i].id.split("-"))[1];
    }
    return checks;
}

function modificar(path, title, id){
        limpiar();
        if(id=="-5")
            id=id+getChecks("itemChecks");
       $.post(path,{"id":id, "title": title},function(responseText) {
        $('#ModalBody').html(responseText);
        $('#exampleModalLabel').html(title);
        });
}

function agregar(path, id){
        limpiar();
        $.post(path,{"id": id , "title": "add"},function(responseText) {
        $('#addModalBody').html(responseText);
        });
}
var del="0";
function eliminar(path, id){
        limpiar();
        if(id.includes(",")){
            del="1"
        }else{
            del="0"
        }
        if(id=="-6")
            id=id+getChecks("listChecks");
        $.post(path,{"id": id , "title": "del"},function(responseText) {
        });
}

function hacerlist(path, tipo){
       $.get(path,function(responseText) {
        $('#Lista').html(responseText);
        $('.offset-area').addClass('show_hide');
        $('.settings-btn').addClass('active');
        });
      }

var clicking= true;
function hacerClick(element,path,id){
       
       if(clicking){
       $.get(path,{"id":id},function(responseText) {
        $('#Principal').html(responseText);
        });}else{
            clicking=true;
        }
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
            if("DIV TR".includes(childs[i].nodeName)){
                response+=childs[i].id.substr(1)+"\n";
                recorrerR(childs[i]);
            }else if("SPAN TD".includes(childs[i].nodeName)){
                response+=childs[i].id.substr(1)+": "+childs[i].textContent.replace(/\n/g,"/n")+"\n";
                recorrerR(childs[i]);
            }
            else{ 
                response+=childs[i].id.substr(1)+": "+childs[i].value.replace(/\n/g,"/n")+"\n";
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
            }else if(childs[i].nodeName=="SPAN"){
                response+=childs[i].id.substr(1)+": "+childs[i].textContent.replace(/\n/g,"/n")+"\n";
                recorrerR(childs[i]);
            }
            else{ 
                response+=childs[i].id.substr(1)+": "+childs[i].value.replace(/\n/g,"/n")+"\n";
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
    $('#InfoMessage').text('Updating...');
    var response= recorrer();
         $.get('/modificar',{"Documento":response},function(responseText) {
        $('#Principal').html(responseText);
        $('#InfoMessage').text('');
        });
}

function add(){
    $('#InfoMessage').text('Adding...');
    var response= recorrer();
         $.get('/guardar',{"Documento":response},function(responseText) {
        $('#Lista').html(responseText);
        $('#InfoMessage').text('');
        });
}

function remove(){
    $('#InfoMessage').text('Deleting...');
    $.get('/eliminar',function(responseText) {
        if(del=="0"){
            $('#Lista').html(responseText);
            $('#Principal').html("");}
        else{
            $('#Principal').html(responseText);
        }
        $('#InfoMessage').text('');
        });
}

// When the user scrolls down 20px from the top of the document, show the button
window.onscroll = function() {scrollFunction()};

function scrollFunction() {
  if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
    document.getElementById("myGoUpBtn").style.display = "block";
  } else {
    document.getElementById("myGoUpBtn").style.display = "none";
  }
}

// When the user clicks on the button, scroll to the top of the document
function topFunction() {
  $("html, body").animate({ scrollTop: 0 }, 600);
}

function composite(path, panel, dir){
    $.get(path,{"path":dir},function(responseText) {
        $('#'+panel).html(responseText);
        });
}

function consulta(select,parameters,async){
    $("#"+select).html("");
    $.ajaxSetup({async:async});
    $.get('/consulta',parameters,function(responseText) {
    var val= $.parseJSON(responseText);
    for (i in val.elements) {
        $("#"+select).append("<option value='"+val.elements[i]+"'>"+val.elements[i]+"</option>");
    }
    }).done(function(){$.ajaxSetup({async:true});});
}

function consultaByElement(select,parameters,async){
    $(select).html("");
    $.ajaxSetup({async:async});
    $.get('/consulta',parameters,function(responseText) {
    var val= $.parseJSON(responseText);
    for (i in val.elements) {
        $(select).append("<option value='"+val.elements[i]+"'>"+val.elements[i]+"</option>");
    }
    }).done(function(){$.ajaxSetup({async:true});});
}