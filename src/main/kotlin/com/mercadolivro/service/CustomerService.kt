package com.mercadolivro.service

import com.mercadolivro.model.CustomerModel
import org.springframework.stereotype.Service

@Service
class CustomerService {
    val customerList = mutableListOf<CustomerModel>()

    fun list(name: String?): List<CustomerModel> {
        name?.let {
            return customerList.filter { it.name.contains(name, true) }
        }
        return customerList
    }

    fun create(customer: CustomerModel) {
        customer.id = if (customerList.isEmpty()) {
            "1"
        } else {
            customerList.last().id!!.toInt() + 1
        }.toString()

        customerList.add(customer)
    }

    fun findById(id: String): CustomerModel {
        return customerList.first { it.id == id }
    }

    fun update(customer: CustomerModel) {
        customerList.first { it.id == customer.id }
            .let {
                it.name = customer.name
                it.email = customer.email
            }
    }

    fun delete(id: String) {
        customerList.removeIf { it.id == id }
    }
}

docker run --name mysql -e MYSQL_ROOT_PASSWORD=secret -d mysql:5