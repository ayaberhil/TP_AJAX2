
import java.util.Date;
import ma.school.beans.Etudiant;
import ma.school.beans.Machine;
import ma.school.beans.Marque;
import ma.school.util.HibernateUtil;
import ma.school.service.EtudiantService;
import ma.school.service.MachineService;
import ma.school.service.MarqueService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author leblond
 */
public class Test {
    public static void main(String[] args) {
        
        EtudiantService es = new EtudiantService();
        es.create(new Etudiant("RAMI", "ALI", "Agadir", new Date(), "homme"));
        es.create(new Etudiant("BEHRIL", "AYA", "El Jadida", new Date(), "femme"));
        es.create(new Etudiant("ESSAOULAJY", "MANAL", "Casa", new Date(), "femme"));
        es.create(new Etudiant("MOUFID", "AMINE", "Rabat", new Date(), "homme"));
        es.create(new Etudiant("MABSOUT", "MOHAMED", "Tanger", new Date(), "homme"));
        es.create(new Etudiant("SAKHR", "YASSINE", "Casa", new Date(), "homme"));
        for(Etudiant e : es.findAll())
            System.out.println(e.getId()+" "+e.getNom());
        
        MarqueService mq = new MarqueService();
        mq.create(new Marque("Code1","APPLE"));
        mq.create(new Marque("Code2","HP"));
        mq.create(new Marque("Code3","DELL"));
        mq.create(new Marque("Code4","LENOVO"));
        
        
        
        MachineService ms = new MachineService();
        ms.create(new Machine("ER43",new Date(),2600.0,mq.findById(1)));
        ms.create(new Machine("EG88",new Date(),4000.0,mq.findById(2)));
        ms.create(new Machine("AB73",new Date(),3999.0,mq.findById(3)));
        ms.create(new Machine("CF30",new Date(),5200.0,mq.findById(3)));
        ms.create(new Machine("FK11",new Date(),8000.0,mq.findById(4)));
        
        
        
    }    
    
}
