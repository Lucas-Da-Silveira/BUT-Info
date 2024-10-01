const redis = require('redis');

const redisUrl = "redis://127.0.0.1:4060";

async function main() {
    const client = redis.createClient({url: redisUrl});

    client.on("error", err => console.log("Redis error : ", err));
    await client.connect();
    console.log("Connected to Redis");

    await client.set("Bonjour", "butS5")
    const value = await client.get("Bonjour");
    console.log(value);

    const redisValues = {
        french: {
            red: "rouge",
            orange: "orange",
            blue: "bleu"
        },
        spanish: {
            red: "rojo",
            orange: "naranja",
            blue: "azul"
        }
    }
    await client.HSET('spanish', 'red', 'rojo');
    await client.HSET('spanish', 'orange', 'naranja');
    await client.HSET('french', 'blue', 'bleu');

    let value2 = await client.HGET("frecnh", "orange");
    let allValue = client.HGETALL("spanish")

    console.log(value2)
    console.log(allValue)

    await client.set("courses",
        JSON.stringify({r510: "newsql"}),
        'EX', 10);

    let courses = await client.get("courses")
    courses = JSON.parse(courses)
    console.log(courses)

    await client.flushAll()
    await client.disconnect();
    console.log("Disconnected from Redis");
}

main().then(() => console.log('Main OK')).catch(err => console.log(err));