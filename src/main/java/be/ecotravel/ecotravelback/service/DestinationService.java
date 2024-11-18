package be.ecotravel.ecotravelback.service;

import be.ecotravel.ecotravelback.entity.Destination;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DestinationService {
    Destination[] activities = new Destination[] {
            new Destination(getNewId(), "Éco-lodge de la Forêt Verte",
                    "Un éco-lodge respectueux de l'environnement situé au cœur d'une forêt luxuriante. Les matériaux de construction sont issus de ressources locales et les énergies renouvelables y sont privilégiées.",
                    "https://ilemauricehotels.com/data/images/hotels-guide/eco-friendly-lodges-guesthouse/eco-friendly-lodges-guesthouse-mauritius%20(1).jpg"),

            new Destination(getNewId(), "Randonnée zéro déchet dans les Alpes",
                    "Une expérience unique pour découvrir les Alpes tout en préservant l’environnement. Les participants sont sensibilisés aux pratiques écoresponsables et invités à respecter le principe du zéro déchet.",
                    "https://blog.ecohotels.com/wp-content/uploads/2024/01/eco-trekking-4.jpg"),

            new Destination(getNewId(), "Séjour à la ferme biologique de la Vallée",
                    "Venez découvrir la vie agricole durable dans une ferme biologique. Participez à des ateliers de permaculture et dégustez des produits locaux et de saison.",
                    "https://files.worldwildlife.org/wwfcmsprod/images/Sustainable_Agriculture/carousel_small/2zv9ndrh1q_sustainable_agriculture_overview_XL_240144.jpg"),

            new Destination(getNewId(), "Camping écologique en bord de mer",
                    "Ce camping propose des emplacements respectueux de l’environnement et des activités de sensibilisation à la protection des littoraux. L’utilisation de produits biodégradables est encouragée.",
                    "https://crrhospitality.com/wp-content/uploads/2023/12/Environmentally-friendly-campsite-development-1024x585.jpg"),

            new Destination(getNewId(), "Balade en vélo électrique dans les vignobles",
                    "Partez à la découverte des vignobles en vélo électrique. Une manière écologique de profiter des paysages tout en réduisant son empreinte carbone.",
                    "https://tripjive.com/wp-content/uploads/2024/05/Pula-sustainable-travel-options-and-eco-friendly-activities.jpg"),

            new Destination(getNewId(), "Initiation à la plongée écoresponsable",
                    "Une activité de plongée où les participants apprennent à respecter les fonds marins, observer sans déranger et découvrir la biodiversité locale de manière durable.",
                    "https://www.abyss.com.au/assets/blog/Eco-friendly-gbr.jpg")
    };

    public Destination[] getPopular() {
        return this.activities;
    }

    public Destination getDestinationById(UUID id){
        for (Destination activity : activities) {
            if (activity.getId() == id) {
                return activity;
            }
        }
        return null;
    }

    //TODO Retirer ça là
    private UUID getNewId() {
        return UUID.randomUUID();
    }
}
