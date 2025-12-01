package br.com.cdb.bancodigital.service;

import br.com.cdb.bancodigital.entity.Seguro;
import br.com.cdb.bancodigital.repository.SeguroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeguroService {
    @Autowired
    private SeguroRepository repo;

    public Seguro criarSeguro(Seguro seguro) {
        return repo.save(seguro);
    }

    public Seguro buscarPorId(Integer id) {
        return repo.findById(id).
                orElseThrow(() -> new RuntimeException("Seguro não encontrado."));
    }

    public String gerarDetalhes(Integer id) {
        Seguro s = buscarPorId(id);

        return """
            ------ DETALHES DO SEGURO ------
            Seguro de Viagem: %s
            Valor Mensal: R$ %s
            Seguro de Fraude: Ativo
            Cobertura: R$ %s
            --------------------------------
            """
                .formatted(
                        s.isSeguroViagemAtivo() ? "Ativo" : "Inativo",
                        s.getValorMensal(),
                        s.getCoberturaFraude()
                );
    }

    public Seguro ativarSeguroViagem(Integer id) {
        Seguro s = buscarPorId(id);
        s.setSeguroViagemAtivo(true);
        return repo.save(s);
    }

    public Seguro desativarSeguroViagem(Integer id) {
        Seguro s = buscarPorId(id);
        s.setSeguroViagemAtivo(false);
        return repo.save(s);
    }
}
