package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.model.CustomerModel
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("customers")
class CustomerController {

    val customerList = mutableListOf<CustomerModel>()

    @GetMapping
    fun list(): List<CustomerModel> {
        return customerList
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody customer: PostCustomerRequest) {
        val id = if (customerList.isEmpty()) {
            "1"
        } else {
            customerList.last().id.toInt() + 1
        }.toString()

        customerList.add(CustomerModel(id, customer.name, customer.email))
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String): CustomerModel {
        return customerList.filter { it.id == id }.first()
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: String, @RequestBody customer: PutCustomerRequest) {
        customerList
            .filter { it.id == id }
            .first()
            .let {
                it.name = customer.name
                it.email = customer.email
            }
    }
}