import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

// Interface TypeScript (comme tes Entity Symfony)
interface Expense {
  id?: number;
  amount: number;
  description: string;
  category: string;
  date: string;
}

@Component({
  selector: 'app-add-expense',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './add-expense.component.html',
  styleUrl: './add-expense.component.scss'
})
export class AddExpenseComponent {
  // Modèle de données (comme $form->getData() en Symfony)
  expense: Expense = {
    amount: 0,
    description: '',
    category: '',
    date: new Date().toISOString().split('T')[0] // Date du jour par défaut
  };

  // Liste des catégories (comme un enum ou une liste fixe)
  categories = [
    'Alimentation',
    'Transport',
    'Logement',
    'Loisirs',
    'Santé',
    'Vêtements',
    'Autre'
  ];

  // État du formulaire
  isSubmitting = false;
  message = '';

  // Méthode appelée lors de la soumission (comme une action de Controller)
  onSubmit() {
    if (this.isValidForm()) {
      this.isSubmitting = true;
      
      // Ici on appellerait l'API Spring Boot
      // this.expenseService.addExpense(this.expense).subscribe(...)
      
      // Simulation pour l'exemple
      setTimeout(() => {
        console.log('Dépense ajoutée:', this.expense);
        this.message = 'Dépense ajoutée avec succès !';
        this.resetForm();
        this.isSubmitting = false;
      }, 1000);
    }
  }

  // Validation côté client (comme les contraintes Symfony)
  isValidForm(): boolean {
    return this.expense.amount > 0 && 
           this.expense.description.trim() !== '' && 
           this.expense.category !== '';
  }

  // Reset du formulaire
  resetForm() {
    this.expense = {
      amount: 0,
      description: '',
      category: '',
      date: new Date().toISOString().split('T')[0]
    };
    this.message = '';
  }

  // Méthode pour formater le montant
  formatAmount(event: any) {
    const value = parseFloat(event.target.value);
    if (!isNaN(value)) {
      this.expense.amount = Math.round(value * 100) / 100; // 2 décimales max
    }
  }
}
