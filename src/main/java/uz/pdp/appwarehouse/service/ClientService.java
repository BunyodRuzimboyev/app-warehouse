package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.entity.Supplier;
import uz.pdp.appwarehouse.payload.ClientDTO;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.SupplierDTO;
import uz.pdp.appwarehouse.repository.ClientRepository;
import uz.pdp.appwarehouse.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public Result addClient(ClientDTO clientDTO) {
        if (clientRepository.existsByPhoneNumber(clientDTO.getPhoneNumber()))
            return new Result("Client's phone number already exist ...", false);

        Client client = new Client();
        client.setName(clientDTO.getName());
        client.setActive(clientDTO.getActive());
        client.setPhoneNumber(clientDTO.getPhoneNumber());
        Client savedClient = clientRepository.save(client);
        return new Result("Client added ...", true, savedClient);
    }

    public Result getAllClients() {
        List<Client> all = clientRepository.findAll();
        return new Result("All supplier's list ...", true, all);
    }

    public Result getOneClient(Integer id) {

        Optional<Client> clientRepositoryById = clientRepository.findById(id);
        if (!clientRepositoryById.isPresent())
            return new Result("Client not found ...", false);

        Client client = clientRepositoryById.get();
        return new Result("Client ...", true, client);
    }

    public Result deleteClient(Integer id) {

        Optional<Client> clientRepositoryById = clientRepository.findById(id);
        if (!clientRepositoryById.isPresent())
            return new Result("Client not found ...", false);

        clientRepository.deleteById(id);
        return new Result("Client deleted ...", true);
    }

    public Result editClient(Integer id, ClientDTO clientDTO) {

        Optional<Client> clientRepositoryById = clientRepository.findById(id);
        if (!clientRepositoryById.isPresent())
            return new Result("Client not found ...", false);

        Client client = new Client();
        client.setName(clientDTO.getName());
        client.setActive(clientDTO.getActive());
        client.setPhoneNumber(clientDTO.getPhoneNumber());
        Client editedClient = clientRepository.save(client);
        return new Result("Supplier edited ...", true, editedClient);
    }


}
