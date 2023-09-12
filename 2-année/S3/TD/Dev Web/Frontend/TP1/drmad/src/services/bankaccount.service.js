import LocalSource from "@/datasource/controller";

function getAccountAmountFromLocalSource(number){
    return LocalSource.getAccountAmount(number);
}

async function getAccountAmount(number){
    let response = null;
    try {
        response = getAccountAmountFromLocalSource(number);
    }
    catch(err) {
        response = {error: 1, status: 404, data: 'impossible de récupérer le montant du compte'  }
    }
    return response;
}

export default {
    getAccountAmount,
}