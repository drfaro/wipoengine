<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Welcome</title>

    <link rel="icon" type="image/x-icon" href="/static/image/favicon.ico">
    <meta charset="utf-8"
          name="viewport"
          content="width=device-width, initial-scale=1.0" />
    
    <link href="webjars/bootstrap/4.1.2/css/bootstrap.min.css"
          rel="stylesheet">

    <link href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
          rel="stylesheet">

    <link href="static/css/style.css" rel="stylesheet">

</head>
<body>
<div id="load"></div>
<div class="alert alert-danger alert-dismissible fade hide" role="alert">
    <span class="text"></span>
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</div>
<div class="alert alert-success alert-dismissible fade hide" role="alert">
    <span class="text"> </span>
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</div>

<div id="linkTo" class="hide"><a href="/"> <input type="button" class="btn submit_btn" value="Cadastrar" ></a></div>

<div class="container internal">
    <div class="row  align-items-end">
        <div class="col-2">

        </div>
        <div class="col">
            <form id="form-internal">
                <div class="input-group form-group row" id="form-search">
                    <div class="input-group-prepend col">
                        <span class="input-group-text"><i class="fas fa-search"></i></span>
                        <input type="text" id="term_search" class="form-control" value="WO2002008677" placeholder="Número de um processo">
                    </div>

                    <div class="col-3">
                        <input type="submit" id="submit_search" data-step="1" value="Pesquisar" class="btn submit_btn">
                    </div>

                </div>
            </form>
        </div>
    </div>

    <div class="row">
        <table class="table table-striped table-dark hide text-truncate" id="table-list">
        <thead>
        <tr>
            <th scope="col">№ de pub</th>
            <th scope="col">Título</th>
            <th scope="col">№ do pedido internacional</th>
            <th scope="col">Requerentes</th>
            <th scope="col">Data de publicação</th>
        </tr>
        </thead>
        <tbody>


        </tbody>
    </table>
    </div>
</div>

<script src="${pageContext.request.contextPath}/webjars/jquery/3.5.0/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/webjars/bootstrap/4.1.2/js/bootstrap.min.js"></script>
<script src="/static/js/util.js"></script>


<script>

    $(document).ready(function (){
        getProcessAll()
        setTimeout(function (){$("#linkTo").slideToggle(1000)}, 2000)

    })


</script>

</body>
</html>