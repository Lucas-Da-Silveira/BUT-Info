document.querySelector("#code_client").addEventListener("change", async (e) => {
    let res = await fetch(`/query_client?code_client=${e.target.value}`);
    res = await res.json();

    if (
        typeof res.prenom === "undefined" &&
        typeof res.nom === "undefined" &&
        typeof res.adresse === "undefined" &&
        typeof res.codePostal === "undefined" &&
        typeof res.ville === "undefined"
    ) {
        document.querySelector("#client_info").innerHTML = "client n'existe pas";
    } else {
        document.querySelector("#client_info").innerHTML = `${res.prenom} ${res.nom}<br>${res.adresse}<br>${res.codePostal} ${res.ville}`;
    }
});

const articles = document.querySelector("#articles");

window.addEventListener("DOMContentLoaded", async (e) => {
    let res = await fetch(`/query_article`);
    res = await res.json();

    for (const articleObj of res) {
        const article = document.createElement("option");
        article.value = articleObj.codePrestation;
        article.textContent = articleObj.prestation;

        articles.append(article);
    }

    await fetchArticleSpec();
});

async function fetchArticleSpec() {
    res = await fetch(`/query_specific_article?id_article=${articles.value}`);
    res = await res.json();

    document.querySelector("#article_spec_id").textContent = res.codePrestation;
    document.querySelector("#article_spec_nom").textContent = res.prestation;
    document.querySelector("#article_spec_prix").textContent = res.prix;
}

articles.addEventListener("change", fetchArticleSpec);

const facture = document.querySelector("#facture");

document.querySelector("#add_row").addEventListener("click", async (e) => {
    let res = await fetch(`/query_article`);
    res = await res.json();

    let newRow = document.createElement("tbody");
    newRow.innerHTML = "<tr>" +
        "<td></td>" +
        "<td><select onchange='updatePrice(this)'><option selected>Non selectionné</option></select></td>" +
        "<td></td>" +
        "<td><input onchange='updateQuantity(this)' type='number' min='0' step='1' value='0'></td>" +
        "<td></td>"
    + "</tr>";

    facture.appendChild(newRow);

    for (const articleObj of res) {
        const article = document.createElement("option");
        article.value = articleObj.codePrestation;
        article.textContent = articleObj.prestation;
        facture.lastChild.firstChild.childNodes[1].childNodes[0].append(article);
    }
});

async function updatePrice(type) {
    let res = await fetch(`/query_specific_article?id_article=${type.value}`);
    res = await res.json();

    let parent = type.parentElement.parentElement;

    type.parentElement.nextSibling.textContent = res.prix;
    parent.firstElementChild.textContent = res.codePrestation;

    parent.lastElementChild.textContent = res.prix * parseFloat(type.parentElement.nextSibling.nextSibling.firstChild.value);

    let sum = 0;

    for (let el of facture.children) {
        el = el.firstElementChild;

        if (isNaN(el.lastElementChild.textContent)) continue;

        sum += parseFloat(el.lastElementChild.textContent);
    }

    document.querySelector("#somme").textContent = sum.toFixed(2);
}

function updateQuantity(qty) {
    let parent = qty.parentElement.parentElement;

    parent.lastElementChild.textContent = (parseFloat(qty.parentElement.previousSibling.textContent) * parseFloat(qty.value)).toFixed(2);

    let sum = 0;

    for (let el of facture.children) {
        el = el.firstElementChild;

        if (isNaN(el.lastElementChild.textContent)) continue;

        sum += parseFloat(el.lastElementChild.textContent);
    }

    document.querySelector("#somme").textContent = sum.toFixed(2);
}

/*
for (let i = 2; i < facture.childNodes.length; i++) {
    facture.childNodes[i].childNodes[1].addEventListener("change", async (e) => {
        let res = await fetch(`/query_specific_article?id_article=${e.target.value}`);
        res = await res.json();

        e.target.nextSibling.textContent = res.prix;
        console.log(res);
    });
}*/
