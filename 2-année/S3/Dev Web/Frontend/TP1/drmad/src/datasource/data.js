/*
Ces tableaux contiennent des données avec la même structure que ce qui est stocké dans la BdD de l'API.
ATTENTION ! Selon la requête utilisée, l'API ne renvoie pas forcément des données avec ce type de structure, mais
peut être avec des champs en moins/plus. Il est donc important de bien spécifier le format des données renvoyées
par l'API pour pouvoir exploiter ces tableaux correctement dans le front-end.


 */

let items=[
  {"_id":"6476f0e1c7e21b2b33a35d78","name":"variole","description":"variole","links":["https://fr.wikipedia.org/wiki/Variole"],"stock":0,"wait":{"$date":"2023-05-31T07:01:53.733Z"},"sold":false,"price":75000,"promotion":[],"object":"{ \"code\": \"ACBCBBACADDCADDBCDDCABBBCADDCADDBCD\"}"},
  {"_id":"6476f0e1c7e21b2b33a35d79","name":"ebola","description":"ebola","links":["https://fr.wikipedia.org/wiki/Virus_Ebola"],"stock":0,"wait":{"$date":"2023-05-31T07:01:53.733Z"},"sold":true,"price":65000,"promotion":[],"object":"{ \"code\": \"AAAABBBBCCCCDDDDDDDDCCCCBBBBAAAA\"}"},
  {"_id":"6476f0e1c7e21b2b33a35d6c","name":"adeno","description":"adenovirus","links":["https://fr.wikipedia.org/wiki/Adenoviridae"],"stock":10,"wait":{"$date":"2023-05-31T07:01:53.73Z"},"sold":true,"price":5000,"promotion":[{"discount":5,"amount":2,"_id":"6476f0e1c7e21b2b33a35d6d"},{"discount":10,"amount":5,"_id":"6476f0e1c7e21b2b33a35d6e"}],"object":"{ \"code\": \"ABABABABABAB\"}"},
  {"_id":"6476f0e1c7e21b2b33a35d6f","name":"covid","description":"covid 19","links":["https://fr.wikipedia.org/wiki/SARS-CoV-2"],"stock":50,"wait":{"$date":"2023-05-31T07:01:53.732Z"},"sold":true,"price":1000,"promotion":[{"discount":10,"amount":10,"_id":"6476f0e1c7e21b2b33a35d70"},{"discount":20,"amount":50,"_id":"6476f0e1c7e21b2b33a35d71"}],"object":"{ \"code\": \"ABADBADCCCBADCBABADBADCCCBADCB\"}"},
  {"_id":"6476f0e1c7e21b2b33a35d72","name":"staphy","description":"staphylocoque","links":["https://fr.wikipedia.org/wiki/Staphylococcus"],"stock":100,"wait":{"$date":"2023-05-31T07:01:53.732Z"},"sold":true,"price":500,"promotion":[{"discount":5,"amount":5,"_id":"6476f0e1c7e21b2b33a35d73"},{"discount":10,"amount":10,"_id":"6476f0e1c7e21b2b33a35d74"},{"discount":20,"amount":50,"_id":"6476f0e1c7e21b2b33a35d75"}],"object":"{ \"code\": \"ABBCDDDDCBBB\"}"},
  {"_id":"6476f0e1c7e21b2b33a35d76","name":"prion","description":"prion","links":["https://fr.wikipedia.org/wiki/Prion_(prot%C3%A9ine)"],"stock":1,"wait":{"$date":"2023-05-31T07:01:53.733Z"},"sold":true,"price":10000,"promotion":[{"discount":10,"amount":2,"_id":"6476f0e1c7e21b2b33a35d77"}],"object":"{ \"code\": \"ABCACACABCAB\"}"},
]
let shopusers=[
  {"_id":"64762dc9066dd654632aed60","name":"tester user","login":"tester","password":"$2a$10$ft4JkdmpHc5iSlOl2e2uNeAST0dJs6EddJEG6CNG24fSXwe/exS56","email":"tester@nowhere.org","session":"00000000-0000-0000-0000-000000000000","orders":[]},
  {"_id":"64762dc9066dd654632aed63","name":"doctor mad","login":"drmad","password":"$2a$10$7oZdoZbfmQs5k0lCl9gTTuooSDQDdFdnLHgk4u7Vf8tE7n0JmRILm","email":"drmad@bad.org","orders":[]},
]
let orders=[
  {"_id":"6476f252a1424611a2a02637","items":[{"item":{"name":"adeno","description":"adenovirus","price":5000,"promotion":[{"discount":5,"amount":2,"_id":"6476f0e1c7e21b2b33a35d6d"},{"discount":10,"amount":5,"_id":"6476f0e1c7e21b2b33a35d6e"}],"object":"{ \"code\": \"ABABABABABAB\"}"},"amount":2,"_id":"6476f252a1424611a2a02638"},{"item":{"name":"covid","description":"covid 19","price":1000,"promotion":[{"discount":10,"amount":10,"_id":"6476f0e1c7e21b2b33a35d70"},{"discount":20,"amount":50,"_id":"6476f0e1c7e21b2b33a35d71"}],"object":"{ \"code\": \"ABADBADCCCBADCBABADBADCCCBADCB\"}"},"amount":1,"_id":"6476f252a1424611a2a0263b"}],"date":{"$date":"2023-05-31T07:08:02.047Z"},"total":10500,"status":"finalized","uuid":"01010101-0000-0000-0000-010101010101"},
]
let bankaccounts=[
  {"_id":"64762dc9066dd654632aed50","number":"FRBADORG78901234567890-0000001","amount":1000000},
  {"_id":"64762dc9066dd654632aed53","number":"FRBADORG78901234567890-0000002","amount":3000000},
  {"_id":"64762dc9066dd654632aed56","number":"FRDRMAD578901234567890-0000666","amount":200000},
]
let transactions=[
  {"_id":"64762dc9066dd654632aed59","amount":-10500,"account":"64762dc9066dd654632aed56","date":{"$date":"2023-05-30T17:09:29.199Z"},"uuid":"99990000-0000-0000-0000-000000000001"},
  {"_id":"64762dc9066dd654632aed5b","amount":75000,"account":"64762dc9066dd654632aed56","date":{"$date":"2023-05-30T17:09:29.201Z"},"uuid":"99990000-0000-0000-0000-000000000002"},
  {"_id":"64762dc9066dd654632aed5d","amount":-75000,"account":"64762dc9066dd654632aed50","date":{"$date":"2023-05-30T17:09:29.202Z"},"uuid":"99990000-0000-0000-0000-000000000003"},
]
let auctionusers=[
  {"_id":"64762dc9066dd654632aed69","pseudo":"drmad","password":"$2a$10$BnPODInoJJJxLcjLDOBL3uHFUQQD9KiM5jfRVoEa0QmTHYSTzL/te","email":"drmad@bad.org","account":"FRDRMAD578901234567890-0000666"},
  {"_id":"64762dc9066dd654632aed6c","pseudo":"crime1","password":"$2a$10$NApPjAFy68l9TQO6iUIwYueK3hiA80tSdbPUZr5FYl5Aje8GPxHfO","email":"crime1@bad.org","account":"FRBADORG78901234567890-0000001"},
  {"_id":"64762dc9066dd654632aed6f","pseudo":"crime2","password":"$2a$10$t7JOXL3n/jQdOmJO7Tf71OJMVBCE83rawRTk/Seb./sIQeB9W1qPO","email":"crime2@bad.org","account":"FRBADORG78901234567890-0000002"},
]
let auctionrequests=[
  {"_id":"64762dc9066dd654632aed72","buyer":"64762dc9066dd654632aed6c","price":50000,"lethality":10,"kidneysVirulence":1,"survival":{"air":100}},
  {"_id":"64762dc9066dd654632aed74","buyer":"64762dc9066dd654632aed6f","price":10000,"lethality":3,"brainVirulence":2,"lungsVirulence":1,"heartVirulence":3,"temperature":{"min":-10,"max":40},"survival":{"ground":10,"water":1000}},
]
let auctiontransactions=[
  {"_id":"64762dc9066dd654632aed77","buyer":{"ref":"64762dc9066dd654632aed6c","pseudo":"crime1","email":"crime1@bad.org","account":"FRBADORG78901234567890-0000001"},"seller":{"ref":"64762dc9066dd654632aed69","pseudo":"drmad","email":"drmad@bad.org","account":"FRDRMAD578901234567890-0000666"},"date":{"$date":"2023-05-30T17:09:29.506Z"},"name":"madovirus","price":75000,"lethality":40,"brainVirulence":9,"lungsVirulence":7,"kidneysVirulence":2,"heartVirulence":2,"temperature":{"min":-30,"max":70},"survival":{"air":1000,"ground":30,"water":200}},
]

export {
  items,
  shopusers,
  orders,
  bankaccounts,
  transactions,
  auctionusers,
  auctionrequests,
  auctiontransactions,
}
