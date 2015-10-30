/*
 * Copyright (c) 2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.example;

import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Singleton
@Path("/menu")
public class PizzaOrder {
    private List<Pizza> orders = new ArrayList<Pizza>();

    public PizzaOrder() {
        Pizza pizza1 = new Pizza();
        pizza1.setId(123);
        pizza1.setName("Cheese Lovers");
        pizza1.setPrice(120);
        Topping p1t1 = new Topping();
        p1t1.setId(987);
        p1t1.setName("Cheese");
        p1t1.setExtraPrice(2.0);
        p1t1.setCategory("Vegetarian");
        pizza1.addTopping(p1t1);
        this.orders.add(pizza1);

        Pizza pizza2 = new Pizza();
        pizza2.setId(124);
        pizza2.setName("Meat Lovers");
        pizza2.setPrice(150);
        Topping p2t1 = new Topping();
        p2t1.setId(988);
        p2t1.setName("Meat Balls");
        p2t1.setExtraPrice(5.0);
        p2t1.setCategory("Non-Vegetarian");
        pizza2.addTopping(p2t1);
        Topping p2t2 = new Topping();
        p2t2.setId(987);
        p2t2.setName("Cheese");
        p2t2.setExtraPrice(2.0);
        p2t2.setCategory("Vegetarian");
        pizza2.addTopping(p2t2);
        this.orders.add(pizza2);
    }

    @GET
    @Path("/order")
    @Produces("application/json")
    public List<Pizza> getOrders() {
        return orders;
    }

    @GET
    @Path("/order/{id}")
    @Produces("application/json")
    public Pizza getOrder(@PathParam("id") int id) {
        for (Pizza order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    @POST
    @Path("/add")
    @Produces("application/json")
    public Response addOrder(Pizza order) {
        orders.add(order);
        return Response.status(201).entity("{\"id\" : \"" + order.getId() + "\"}").type("application/json").build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteOrder(@PathParam("id") int id) {
        orders.removeIf(order -> order.getId() == id);
        return Response.status(204).build();
    }
}
