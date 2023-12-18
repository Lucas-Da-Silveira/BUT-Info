let items=[
{"_id":"654512860a4bd1c34897416d","name":"adeno","description":"adenovirus","links":["https://fr.wikipedia.org/wiki/Adenoviridae"],"stock":10,"wait":{"$date":"2023-11-03T15:32:22.862Z"},"sold":true,"price":5000,"promotion":[{"discount":5,"amount":2,"_id":"654512860a4bd1c34897416e"},{"discount":10,"amount":5,"_id":"654512860a4bd1c34897416f"}],"object":"{ \"code\": \"ABABABABABAB\"}"},
{"_id":"654512860a4bd1c348974172","name":"covid","description":"covid 19","links":["https://fr.wikipedia.org/wiki/SARS-CoV-2"],"stock":50,"wait":{"$date":"2023-11-03T15:32:22.937Z"},"sold":true,"price":1000,"promotion":[{"discount":10,"amount":10,"_id":"654512860a4bd1c348974173"},{"discount":20,"amount":50,"_id":"654512860a4bd1c348974174"}],"object":"{ \"code\": \"ABADBADCCCBADCBABADBADCCCBADCB\"}"},
{"_id":"654512860a4bd1c348974177","name":"staphy","description":"staphylocoque","links":["https://fr.wikipedia.org/wiki/Staphylococcus"],"stock":100,"wait":{"$date":"2023-11-03T15:32:22.95Z"},"sold":true,"price":500,"promotion":[{"discount":5,"amount":5,"_id":"654512860a4bd1c348974178"},{"discount":10,"amount":10,"_id":"654512860a4bd1c348974179"},{"discount":20,"amount":50,"_id":"654512860a4bd1c34897417a"}],"object":"{ \"code\": \"ABBCDDDDCBBB\"}"},
{"_id":"654512860a4bd1c34897417d","name":"prion","description":"prion","links":["https://fr.wikipedia.org/wiki/Prion_(prot%C3%A9ine)"],"stock":1,"wait":{"$date":"2023-11-03T15:32:22.956Z"},"sold":true,"price":10000,"promotion":[{"discount":10,"amount":2,"_id":"654512860a4bd1c34897417e"}],"object":"{ \"code\": \"ABCACACABCAB\"}"},
{"_id":"654512860a4bd1c348974181","name":"variole","description":"variole","links":["https://fr.wikipedia.org/wiki/Variole"],"stock":0,"wait":{"$date":"2023-11-03T15:32:22.959Z"},"sold":false,"price":75000,"promotion":[],"object":"{ \"code\": \"ACBCBBACADDCADDBCDDCABBBCADDCADDBCD\"}"},
{"_id":"654512860a4bd1c348974184","name":"ebola","description":"ebola","links":["https://fr.wikipedia.org/wiki/Virus_Ebola"],"stock":0,"wait":{"$date":"2023-11-03T15:32:22.961Z"},"sold":true,"price":65000,"promotion":[],"object":"{ \"code\": \"AAAABBBBCCCCDDDDDDDDCCCCBBBBAAAA\"}"},
]
let shopusers=[
{"_id":"654512870a4bd1c34897418b","name":"tester user","login":"tester","password":"$2a$10$Hx0rFxI7zVoeHmaevuyQCOYwqUogf8yfMrEjIluwRnJjDNlEDkaJC","email":"tester@nowhere.org","session":"00000000-0000-0000-0000-000000000000","orders":[]},
{"_id":"654512870a4bd1c34897418e","name":"doctor mad","login":"drmad","password":"$2a$10$UwJos/mn1QQ3crdmKYNpu.qPXRHcXDRtlv3PWM3fj4/a/TbrYaXtS","email":"drmad@bad.org","orders":[{"items":[{"item":{"name":"adeno","description":"adenovirus","price":5000,"promotion":[{"discount":5,"amount":2,"_id":"654512860a4bd1c34897416e"},{"discount":10,"amount":5,"_id":"654512860a4bd1c34897416f"}],"object":"{ \"code\": \"ABABABABABAB\"}"},"amount":2,"_id":"654512870a4bd1c348974190"},{"item":{"name":"covid","description":"covid 19","price":1000,"promotion":[{"discount":10,"amount":10,"_id":"654512860a4bd1c348974173"},{"discount":20,"amount":50,"_id":"654512860a4bd1c348974174"}],"object":"{ \"code\": \"ABADBADCCCBADCBABADBADCCCBADCB\"}"},"amount":1,"_id":"654512870a4bd1c348974193"}],"date":{"$date":"2023-11-03T15:32:23.076Z"},"total":10500,"status":"finalized","uuid":"01010101-0000-0000-0000-010101010101","_id":"654512870a4bd1c34897418f"}]},
]
let orders=[
]
let bankaccounts=[
{"_id":"65450e3e66a7639cbe9ee273","number":"FRBADORG78901234567890-0000001","amount":1000000},
{"_id":"65450e3e66a7639cbe9ee276","number":"FRBADORG78901234567890-0000002","amount":3000000},
{"_id":"65450e3e66a7639cbe9ee279","number":"FRDRMAD578901234567890-0000666","amount":200000},
{"_id":"6547cb118916a76757d0d21c","number":"FRSHOP4578901234567890-0000999","amount":200000},
]
let transactions=[
{"_id":"6547cb118916a76757d0d21f","amount":-10500,"account":"65450e3e66a7639cbe9ee279","destination":"6547cb118916a76757d0d21c","date":{"$date":"2023-11-05T17:04:17.66Z"},"uuid":"99990000-0000-0000-0000-000000000001"},
{"_id":"6547cb118916a76757d0d221","amount":75000,"account":"65450e3e66a7639cbe9ee279","date":{"$date":"2023-11-05T17:04:17.661Z"},"uuid":"99990000-0000-0000-0000-000000000002"},
{"_id":"6547cb118916a76757d0d223","amount":-75000,"account":"65450e3e66a7639cbe9ee273","date":{"$date":"2023-11-05T17:04:17.662Z"},"uuid":"99990000-0000-0000-0000-000000000003"},
]
let auctionusers=[
{"_id":"65450e3e66a7639cbe9ee28c","pseudo":"drmad","password":"$2a$10$4vcMhFQUjnFcOIoTf7CDiehXKJfC0/bvw1mzVMu9NOWsTpufY7rFC","email":"drmad@bad.org","account":"FRDRMAD578901234567890-0000666"},
{"_id":"65450e3e66a7639cbe9ee28f","pseudo":"crime1","password":"$2a$10$Wiw0gFWY5Wiy2ysQbz.BZuDcGKYsT1eMzqQJEy89NmsCxpuRBqF8G","email":"crime1@bad.org","account":"FRBADORG78901234567890-0000001"},
{"_id":"65450e3e66a7639cbe9ee292","pseudo":"crime2","password":"$2a$10$g6NKwvvL2ihOdG7bWAQYne3dVUj24q/ljM9OqqDQj.LuxRhKJ2Baa","email":"crime2@bad.org","account":"FRBADORG78901234567890-0000002"},
]
let auctionrequests=[
{"_id":"65450e3e66a7639cbe9ee295","buyer":"65450e3e66a7639cbe9ee28f","price":50000,"lethality":10,"kidneysVirulence":1,"survival":{"air":100}},
{"_id":"65450e3e66a7639cbe9ee297","buyer":"65450e3e66a7639cbe9ee292","price":10000,"lethality":3,"brainVirulence":2,"lungsVirulence":1,"heartVirulence":3,"temperature":{"min":-10,"max":40},"survival":{"ground":10,"water":1000}},
]
let auctiontransactions=[
{"_id":"65450e3e66a7639cbe9ee29a","buyer":{"ref":"65450e3e66a7639cbe9ee28f","pseudo":"crime1","email":"crime1@bad.org","account":"FRBADORG78901234567890-0000001"},"seller":{"ref":"65450e3e66a7639cbe9ee28c","pseudo":"drmad","email":"drmad@bad.org","account":"FRDRMAD578901234567890-0000666"},"date":{"$date":"2023-11-03T15:14:06.448Z"},"name":"madovirus","price":75000,"lethality":40,"brainVirulence":9,"lungsVirulence":7,"kidneysVirulence":2,"heartVirulence":2,"temperature":{"min":-30,"max":70},"survival":{"air":1000,"ground":30,"water":200}},
]

module.exports = {
   items,
   shopusers,
   orders,
   bankaccounts,
   transactions,
   auctionusers,
   auctionrequests,
   auctiontransactions,
}