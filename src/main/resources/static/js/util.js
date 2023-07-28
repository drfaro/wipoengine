

var objProcess
function showMessageError(msg) {
    $(".alert-danger").removeClass("hide")
    $(".alert-danger").addClass("show")
    $(".alert-danger span.text").html(msg)
    setTimeout(removeMessage, 7000)
    //alert-success
}
function showMessageSuccess(msg) {
    $(".alert-success").removeClass("hide")
    $(".alert-success").addClass("show")
    $(".alert-success span.text").html(msg)
    setTimeout(removeMessage, 7000)
    //
}
function removeMessage(){
    $(".alert").addClass("hide")
    $(".alert").removeClass("show")
}

$(".close").click(function (){
    $(".alert").addClass("hide")
    $(".alert").removeClass("show")
});

function getCrawlerProcess(id) {
    console.log("getCrawlerProcess");

    var jqxhr = $.get( "/crawler/"+id, function() {
        //showMessageSuccess( "success" );
    })

        .done(function(data) {
            load()
            toggle()
            objProcess = data
            $("#input_title").val(objProcess.title)
            $("#input_pub").val(objProcess.externalIdProcess)
            $("#input_internacional_order").val(objProcess.internationalOrder)
            $("#input_publication_date").val(objProcess.publicationDate)
            $("#input_claimant").val(objProcess.claimant)
            $("#back").toggle()
            $("#title").html("Cadastro")
            $("#submit").attr("value","Cadastrar")
            $("#submit").attr("data-step","2")

        })
        .fail(function() {
            load()
            showMessageError( "Ocorreu um erro de comunica&ccedil;&atilde;o com esse servi&ccedil;o, por favor tente mais tarde" );

        });

    return false;

}

function postProcess() {

    var jqxhr = $.post({
        url: "/process",
        contentType: "application/json",
        dataType: "json",
    }, JSON.stringify(objProcess), function() {
        showMessageSucess( "Processo Cadastro com Sucesso!" );
    })
        .done(function(data) {
            load()
            toggle()
            $("#back").toggle()
            console.log(data)
            $("#title").html("Pesquisa")
            $("#submit").attr("value","Pesquisar")
            $("#submit").attr("data-step","1")

        })
        .fail(function() {
            //$("#back").toggle()
            load()
            showMessageError( "Ocorreu um erro de comunicação com esse serviço, por favor tente mais tarde" );

        })
        .always(function() {},"json");

    return false;

}
//Todo: validação do formulario
function toggle(){
    $("#form-search").slideToggle(500)
    $("#form-register").slideToggle(500)
}

function load(){
    $("#load").slideToggle(500)
}


$("#back").click(function() {
    $("#title").html("Pesquisa")
    $("#submit").attr("value","Pesquisar")
    $("#submit").attr("data-step","1")
    $("#back").toggle()
    toggle();
});

$("#form").submit(function() {
    load();
    var dataStep = $("#submit").attr("data-step")
    if (dataStep == "1"){

        var termSearch = $("#term_search").val()
        getCrawlerProcess(termSearch)
    }
    if (dataStep == "2"){
        postProcess()
    }
    return false;
});



//===========================================================

let tr = ""
function foreachProcess(objProcess) {
    console.log(objProcess)
    let tbody = $("#table-list tbody")

    tbody.html("")
    tbody.slideToggle(500)

    for (let objProcessKey in objProcess) {

        tr = '<tr>'
        tr +='<th class="text-truncate" data-toggle="tooltip" data-placement="top" title="'+objProcess[objProcessKey].externalIdProcess+'"  scope="row"><span class="d-inline-block text-truncate" style="max-width: 150px;">'+objProcess[objProcessKey].externalIdProcess+'</span></th>'
        tr +='<td class="text-truncate" data-toggle="tooltip" data-placement="top" title="'+objProcess[objProcessKey].title+'"> <span class="d-inline-block text-truncate" style="max-width: 150px;">'+objProcess[objProcessKey].title+'</span></td>'
        tr +='<td class="text-truncate" data-toggle="tooltip" data-placement="top" title="'+objProcess[objProcessKey].internationalOrder+'"> <span class="d-inline-block text-truncate" style="max-width: 150px;">'+objProcess[objProcessKey].internationalOrder+'</span></td>'
        tr +='<td class="text-truncate" data-toggle="tooltip" data-placement="top" title="'+objProcess[objProcessKey].claimant+'"> <span class="d-inline-block text-truncate" style="max-width: 150px;">'+objProcess[objProcessKey].claimant+'</span></td>'
        tr +='<td class="text-truncate" data-toggle="tooltip" data-placement="top" title="'+objProcess[objProcessKey].publicationDate+'"> <span class="d-inline-block text-truncate" style="max-width: 150px;">'+objProcess[objProcessKey].publicationDate+'</span></td>'
        tr +='</tr>'

        $("#table-list tbody").append(tr)
    }

    tbody.slideToggle(500)
}

function getProcessAll() {
    console.log("getProcessAll");

    let jqxhr = $.get( "/process", function() {
        console.log( "success" );
    }).done(function(objProcess) {
        foreachProcess(objProcess)
    })
        .fail(function() {
            console.log( "error" );
        })
        .always(function() {
            console.log( "finished" );
        });

    jqxhr.always(function() {
        loadInternal()
    });
    return false;

}
function getProcessDatabase(term) {
    console.log("getCrawlerProcess");

    let jqxhr = $.get( "/process/search/"+term, function() {
        console.log( "success" );
    }).done(function(objProcess) {
        foreachProcess(objProcess)
    })
        .fail(function() {
            console.log( "error" );
        })
        .always(function() {
            console.log( "finished" );
        });

    jqxhr.always(function() {
        load()
    });
    return false;

}

//Todo: validação do formulario


function loadInternal(){
    $("#load").slideToggle(500)
    $("#table-list").slideToggle(500)
}



$("#form-internal").submit(function() {
    loadInternal();
    let termSearch = $("#term_search").val()
    getProcessDatabase(termSearch)
    return false;
});
