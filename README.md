## Laboratorio de Computación III

API REST desarrollada en Java con Spring-Boot.
Cada producto creado debe pertenecer a una categoria por lo que es necesario crear una categoría ANTES de crear un producto. 


# Categories
· GET /categories ---> trae todas las categorías creadas.<br />
· POST /categories --> crea una categoría<br />
· GET /categories/id --> trae una categoría específica por el id provisto.<br />
· PUT /categories/id --> edita una categoria creada por el id provisto<br />
· DELETE /categories/id --> borra una categoría creada por el id provisto. <br />
· GET /categories/brand?brand="brand_name" --> trae las categorías que contengan productos con determinada marca. <br />
· GET /categories/order?order_price="asc" or "desc" --> trae las categorías con productos ordenados por precio de forma ascendiente (asc) o descendiente (desc)<br />
· GET /categories/price?price_min=10&price_max=20 --> trae las categorias con productos filtrados por un precio minimo y un precio maximo provisto<br />

# Products
· GET /products --> trae todos los productos creados.<br />
· POST /products --> crea un producto.<br />
· GET /products/id --> trae un producto especifico por el id provisto.<br />
· PUT /products/id --> edita un producto especifico por el id provisto.<br />
· DELETE /products/id --> borra un producto especifico por e id provisto. <br />
· GET /products/filter?name="product"&brand="brand"&cat="category" --> Trae los productos filtrados mediante los parametros enviados (nombre del producto, marca y categoria)<br />
